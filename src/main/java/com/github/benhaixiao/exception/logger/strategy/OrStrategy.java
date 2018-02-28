package com.github.benhaixiao.exception.logger.strategy;

import com.google.common.base.Strings;
import com.google.common.primitives.Ints;

/**
 * 逻辑或策略. 用于组合两个基础策略.
 *
 * @author xiaobenhai
 */
public class OrStrategy implements Strategy {

    private Strategy first;
    private Strategy second;

    public OrStrategy(Strategy first, Strategy second) {
        this.first = first == null ? new NoneStrategy() : first;
        this.second = second == null ? new NoneStrategy() : second;
    }

    @Override
    public boolean isOverflow() {
        return first.isOverflow() || second.isOverflow();
    }

    @Override
    public String getName() {
        if (Strings.isNullOrEmpty(first.getName())) {
            return second.getName();
        }
        if (Strings.isNullOrEmpty(second.getName())) {
            return first.getName();
        }
        return first.getName() + "-or-" + second.getName();
    }

    @Override
    public int addUpTotal() {
        return Ints.max(first.addUpTotal(), second.addUpTotal());
    }

    @Override
    public int addUpError() {
        return Ints.max(first.addUpError(), second.addUpError());
    }

    @Override
    public String toString() {
        return "OrStrategy{" +
               "first=" + first +
               ", second=" + second +
               '}';
    }
}
