package com.github.benhaixiao.exception.logger.factory;

import com.github.benhaixiao.exception.logger.ExceptionLogger;
import com.github.benhaixiao.exception.logger.strategy.FrequencyStrategy;
import com.github.benhaixiao.exception.logger.strategy.Strategy;

/**
 * @author xiaobenhai
 */
public class FrequencyLoggerFactory extends AbstractExceptionLoggerFactory {

    private int frequencyTime;
    private int frequency;

    public void init(int frequencyTime, int frequency) {
        this.frequencyTime = frequencyTime;
        this.frequency = frequency;
    }

    @Override
    protected ExceptionLogger build() {
        Strategy strategy = new FrequencyStrategy(frequencyTime, frequency);

        return new ExceptionLogger(strategy);
    }

    public void setFrequencyTime(int frequencyTime) {
        this.frequencyTime = frequencyTime;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
