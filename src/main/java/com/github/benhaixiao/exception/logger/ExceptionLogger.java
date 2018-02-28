package com.github.benhaixiao.exception.logger;

import com.github.benhaixiao.exception.logger.strategy.AndStrategy;
import com.github.benhaixiao.exception.logger.strategy.NoneStrategy;
import com.github.benhaixiao.exception.logger.strategy.Strategy;

import org.slf4j.Logger;

/**
 * 异常日志工具类.
 *
 * <b>在调用实际方法前, 先调用{@link #addUpTotal()}, 记录方法的总调用次数, 用于计算出错的比例.</b>
 *
 * @author xiaobenhai
 */
public class ExceptionLogger {

    private Strategy currentStrategy = new NoneStrategy();

    public ExceptionLogger(Strategy strategy) {
        if (strategy != null) {
            currentStrategy = strategy;
        }
    }

    public ExceptionLogger addStrategy(Strategy strategy) {
        if (strategy == null) {
            return this;
        }
        currentStrategy = new AndStrategy(currentStrategy, strategy);
        return this;
    }

    public ExceptionLogger error(Logger logger, String format, String argument) {
        return error(logger, format, new String[]{argument});
    }

    public ExceptionLogger error(Logger logger, String format, Object... arguments) {
        currentStrategy.addUpError();

        if (currentStrategy.isOverflow()) {
            logger.error(format, arguments);
        } else {
            logger.warn(format, arguments);
        }

        return this;
    }

    public ExceptionLogger error(Logger logger, String msg) {
        currentStrategy.addUpError();

        if (currentStrategy.isOverflow()) {
            logger.error(msg);
        } else {
            logger.warn(msg);
        }

        return this;
    }

    public ExceptionLogger error(Logger logger, String msg, Throwable t) {
        currentStrategy.addUpError();

        if (currentStrategy.isOverflow()) {
            logger.error(msg, t);
        } else {
            logger.warn(msg, t);
        }

        return this;
    }

    public int addUpTotal() {
        return currentStrategy.addUpTotal();
    }

    public String getStrategyName() {
        return currentStrategy.getName();
    }

    public boolean isOverflow() {
        return currentStrategy.isOverflow();
    }

    public boolean addUpErrorAndIsOverflow() {
        currentStrategy.addUpError();
        return isOverflow();
    }

}
