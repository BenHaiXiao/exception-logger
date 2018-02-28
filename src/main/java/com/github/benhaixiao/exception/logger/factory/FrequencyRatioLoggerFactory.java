package com.github.benhaixiao.exception.logger.factory;

import com.github.benhaixiao.exception.logger.ExceptionLogger;
import com.github.benhaixiao.exception.logger.strategy.AndStrategy;
import com.github.benhaixiao.exception.logger.strategy.FrequencyStrategy;
import com.github.benhaixiao.exception.logger.strategy.OrStrategy;
import com.github.benhaixiao.exception.logger.strategy.RatioStrategy;
import com.github.benhaixiao.exception.logger.strategy.Strategy;

/**
 * @author xiaobenhai
 */
public class FrequencyRatioLoggerFactory extends AbstractExceptionLoggerFactory {

    private int frequencyTime;
    private int frequency;

    private int ratioTime;
    private int ratio;

    private boolean useOrLogic = true;

    public void init(int frequencyTime, int frequency, int ratioTime, int ratio, boolean useOrLogic) {
        setFrequencyTime(frequencyTime);
        setFrequency(frequency);
        setRatioTime(ratioTime);
        setRatio(ratio);
        setUseOrLogic(useOrLogic);
    }

    @Override
    protected ExceptionLogger build() {
        Strategy frequencyStrategy = new FrequencyStrategy(frequencyTime, frequency);
        Strategy ratioStrategy = new RatioStrategy(ratioTime, ratio);
        Strategy strategy = useOrLogic ? new OrStrategy(frequencyStrategy, ratioStrategy) : new AndStrategy(frequencyStrategy, ratioStrategy);

        return new ExceptionLogger(strategy);
    }

    public void setFrequencyTime(int frequencyTime) {
        this.frequencyTime = frequencyTime;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void setRatioTime(int ratioTime) {
        this.ratioTime = ratioTime;
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
    }

    public void setUseOrLogic(boolean useOrLogic) {
        this.useOrLogic = useOrLogic;
    }

}
