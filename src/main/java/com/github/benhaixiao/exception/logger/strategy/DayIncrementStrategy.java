package com.github.benhaixiao.exception.logger.strategy;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 按天增量策略. 按天统计达到一定增量触发.
 *
 * @author xiaobenhai
 */
public class DayIncrementStrategy implements Strategy {

    private static final String CACHE_KEY = "day-increment";

    private static Logger logger = LoggerFactory.getLogger(DayIncrementStrategy.class);

    private int threshold;
    private int increment;

    private Cache<String, CachedValue> cache;

    public DayIncrementStrategy(int threshold, int increment) {
        this.threshold = threshold;
        this.increment = increment;

        cache = CacheBuilder.newBuilder().expireAfterWrite(getRemainingTime(), TimeUnit.MILLISECONDS).removalListener(
                new RemovalListener<String, CachedValue>() {
                    @Override
                    public void onRemoval(RemovalNotification<String, CachedValue> notification) {
                        final int total = notification.getValue().getTotal();
                        final int error = notification.getValue().getError();
                        logger.warn("{} : total = {} ; error = {}", new Object[]{getName(), total, error});
                        rebuildCache();
                    }
                }).build();
    }

    private static long getRemainingTime() {
        DateTime nowTime = new DateTime();
        DateTime endOfDay = nowTime.millisOfDay().withMaximumValue();
        return endOfDay.getMillis() - nowTime.getMillis();
    }

    private void rebuildCache() {
        cache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).removalListener(new RemovalListener<String, CachedValue>() {
            @Override
            public void onRemoval(RemovalNotification<String, CachedValue> notification) {
                final int total = notification.getValue().getTotal();
                final int error = notification.getValue().getError();
                logger.warn("{} : total = {} ; error = {}", new Object[]{getName(), total, error});
            }
        }).build();
    }

    @Override
    public boolean isOverflow() {
        int error = getCachedValue().getError();
        return error >= threshold && (error - threshold) % increment == 0;
    }

    @Override
    public String getName() {
        return "day-increment-strategy";
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
        return "DayIncrementStrategy{" +
               "threshold=" + threshold +
               ", increment=" + increment +
               '}';
    }
}
