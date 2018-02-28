package com.github.benhaixiao.exception.logger.factory;

import com.github.benhaixiao.exception.logger.ExceptionLogger;
import com.github.benhaixiao.exception.logger.strategy.DayIncrementStrategy;
import com.github.benhaixiao.exception.logger.strategy.Strategy;

/**
 * @author xiaobenhai
 */
public class DayIncrementLoggerFactory extends AbstractExceptionLoggerFactory {

    private int threshold;
    private int increment;

    public void init(int threshold, int increment) {
        setThreshold(threshold);
        setIncrement(increment);
    }

    @Override
    protected ExceptionLogger build() {
        Strategy strategy = new DayIncrementStrategy(threshold, increment);
        return new ExceptionLogger(strategy);
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public void setIncrement(int increment) {
        this.increment = increment;
    }
}
