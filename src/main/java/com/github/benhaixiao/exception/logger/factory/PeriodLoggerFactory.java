package com.github.benhaixiao.exception.logger.factory;

import com.github.benhaixiao.exception.logger.ExceptionLogger;
import com.github.benhaixiao.exception.logger.strategy.PeriodStrategy;
import com.github.benhaixiao.exception.logger.strategy.Strategy;

/**
 * @author xiaobenhai
 */
public class PeriodLoggerFactory extends AbstractExceptionLoggerFactory {

    private int period;
    private int threshold;

    public void init(int period, int threshold) {
        setPeriod(period);
        setThreshold(threshold);
    }

    @Override
    protected ExceptionLogger build() {
        Strategy strategy = new PeriodStrategy(period, threshold);

        return new ExceptionLogger(strategy);
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

}
