package com.github.benhaixiao.exception.logger.strategy;

/**
 * 无行为的策略. NullObject设计模式.
 *
 * @author xiaobenhai
 */
public class NoneStrategy implements Strategy {

    @Override
    public boolean isOverflow() {
        return true;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public int addUpTotal() {
        return 0;
    }

    @Override
    public int addUpError() {
        return 0;
    }

    @Override
    public String toString() {
        return "NoneStrategy{}";
    }
}
