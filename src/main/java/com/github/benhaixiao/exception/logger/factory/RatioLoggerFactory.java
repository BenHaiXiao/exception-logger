package com.github.benhaixiao.exception.logger.factory;

import com.github.benhaixiao.exception.logger.ExceptionLogger;
import com.github.benhaixiao.exception.logger.strategy.RatioStrategy;
import com.github.benhaixiao.exception.logger.strategy.Strategy;

/**
 * @author xiaobenhai
 */
public class RatioLoggerFactory extends AbstractExceptionLoggerFactory {

    private int ratioTime;
    private int ratio;

    public void init(int ratioTime, int ratio) {
        setRatioTime(ratioTime);
        setRatio(ratio);
    }

    @Override
    protected ExceptionLogger build() {
        Strategy strategy = new RatioStrategy(ratioTime, ratio);

        return new ExceptionLogger(strategy);
    }

    public void setRatioTime(int ratioTime) {
        this.ratioTime = ratioTime;
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
    }
}
