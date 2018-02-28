package com.github.benhaixiao.exception.logger.strategy;

import com.google.common.base.Strings;

/**
 * 逻辑非策略.
 *
 * @author xiaobenhai
 */
public class NotStrategy implements Strategy {

    private Strategy strategy;

    public NotStrategy(Strategy strategy) {
        this.strategy = strategy == null ? new NoneStrategy() : strategy;
    }

    @Override
    public boolean isOverflow() {
        return !strategy.isOverflow();
    }

    @Override
    public String getName() {
        return Strings.isNullOrEmpty(strategy.getName()) ? "" : "not-" + strategy.getName();
    }

    @Override
    public int addUpTotal() {
        return strategy.addUpTotal();
    }

    @Override
    public int addUpError() {
        return strategy.addUpError();
    }

    @Override
    public String toString() {
        return "NotStrategy{" +
               "strategy=" + strategy +
               '}';
    }
}
