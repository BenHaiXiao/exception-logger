package com.github.benhaixiao.exception.logger.factory;

import com.github.benhaixiao.exception.logger.ExceptionLogger;
import com.github.benhaixiao.exception.logger.strategy.AndStrategy;
import com.github.benhaixiao.exception.logger.strategy.FrequencyStrategy;
import com.github.benhaixiao.exception.logger.strategy.OrStrategy;
import com.github.benhaixiao.exception.logger.strategy.PeriodStrategy;
import com.github.benhaixiao.exception.logger.strategy.Strategy;

/**
 * @author Xu.zhipeng
 */
public class FrequencyPeriodLoggerFactory extends AbstractExceptionLoggerFactory {

    private int frequencyTime;
    private int frequency;

    private int period;
    private int threshold;

    private boolean useOrLogic = true;

    public void init(int frequencyTime, int frequency, int period, int threshold, boolean useOrLogic) {
        this.frequencyTime = frequencyTime;
        this.frequency = frequency;
        this.period = period;
        this.threshold = threshold;
        this.useOrLogic = useOrLogic;
    }

    public void init(int frequencyTime, int frequency, int period, int threshold) {
        this.frequencyTime = frequencyTime;
        this.frequency = frequency;
        this.period = period;
        this.threshold = threshold;
    }

    @Override
    protected ExceptionLogger build() {
        Strategy frequencyStrategy = new FrequencyStrategy(frequencyTime, frequency);
        Strategy periodStrategy = new PeriodStrategy(period, threshold);
        Strategy strategy = useOrLogic ? new OrStrategy(frequencyStrategy, periodStrategy) : new AndStrategy(frequencyStrategy, periodStrategy);

        return new ExceptionLogger(strategy);
    }

    public void setFrequencyTime(int frequencyTime) {
        this.frequencyTime = frequencyTime;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public void setUseOrLogic(boolean useOrLogic) {
        this.useOrLogic = useOrLogic;
    }
}
