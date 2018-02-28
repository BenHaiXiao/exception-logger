package com.github.benhaixiao.exception.logger.strategy;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 比例策略. 一定时间内(单位: s)超过一定的比例就触发.
 *
 * @author xiaobenhai
 */
public class RatioStrategy implements Strategy {

    private static final String CACHE_KEY = "ratio";

    private static Logger logger = LoggerFactory.getLogger(RatioStrategy.class);

    private int time;
    private int ratio;

    private Cache<String, CachedValue> cache;

    public RatioStrategy(int time, int ratio) {
        this.time = time;
        this.ratio = ratio;

        this.cache = CacheBuilder.newBuilder().expireAfterWrite(time, TimeUnit.SECONDS).build();
    }

    @Override
    public boolean isOverflow() {
        return getCachedValue().getRatio() >= ratio;
    }

    @Override
    public String getName() {
        return "ratio-strategy";
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
        return "RatioStrategy{" +
               "time=" + time +
               ", ratio=" + ratio +
               '}';
    }
}
