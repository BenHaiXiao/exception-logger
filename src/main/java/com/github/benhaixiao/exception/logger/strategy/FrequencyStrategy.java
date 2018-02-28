package com.github.benhaixiao.exception.logger.strategy;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 频率策略. 一定时间内(单位: s)超过一定的数量就触发.
 *
 * @author xiaobenhai
 */
public class FrequencyStrategy implements Strategy {

    private static final String CACHE_KEY = "frequency";

    private static Logger logger = LoggerFactory.getLogger(FrequencyStrategy.class);

    private int time;
    private int frequency;

    private Cache<String, CachedValue> cache;

    public FrequencyStrategy(int time, int frequency) {
        this.time = time;
        this.frequency = frequency;

        this.cache = CacheBuilder.newBuilder().expireAfterWrite(time, TimeUnit.SECONDS).build();
    }

    @Override
    public boolean isOverflow() {
        return getCachedValue().getError() >= frequency;
    }

    @Override
    public String getName() {
        return "frequency-strategy";
    }

    @Override
    public int addUpTotal() {
        return getCachedValue().addUpTotal();
    }

    @Override
    public int addUpError() {
        return getCachedValue().addUpError();
    }

    private CachedValue getCachedValue() {
        try {
            return cache.get(CACHE_KEY, new CachedValueCreator());
        } catch (ExecutionException e) {
            logger.warn("{} : error {}", new Object[]{getName(), e});
        }
        return new CachedValue();
    }

    @Override
    public String toString() {
        return "FrequencyStrategy{" +
               "time=" + time +
               ", frequency=" + frequency +
               '}';
    }

}
