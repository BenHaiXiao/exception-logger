package com.github.benhaixiao.exception.logger.strategy;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;

/**
 * 周期数量策略。 一定调用周期(次数)之内，超过一定error数量时触发。
 *
 * @author xiaobenhai
 */
public class PeriodStrategy implements Strategy {

    private static final String CACHE_KEY = "period";

    private static Logger logger = LoggerFactory.getLogger(PeriodStrategy.class);

    private int period;
    private int threshold;

    private Cache<String, CachedValue> cache;

    public PeriodStrategy(int period, int threshold) {
        this.period = period;
        this.threshold = threshold;

        this.cache = CacheBuilder.newBuilder().softValues().build();
    }

    @Override
    public boolean isOverflow() {
        return getCachedValue().getError() >= threshold;
    }

    @Override
    public String getName() {
        return "period-strategy";
    }

    @Override
    public int addUpTotal() {
        CachedValue cachedValue = getCachedValue();
        if (cachedValue.getTotal() >= period) {
            cachedValue = new CachedValue();
            cache.put(CACHE_KEY, cachedValue);
        }
        return cachedValue.addUpTotal();
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
        return "PeriodStrategy{" +
               "period=" + period +
               ", threshold=" + threshold +
               '}';
    }
}
