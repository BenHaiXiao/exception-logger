package com.github.benhaixiao.exception.logger.strategy;

/**
 * 打印Error日志的策略.
 *
 * @author xiaobenhai
 */
public interface Strategy {

    /**
     * 是否达到阀值, 进行error日志的输出?
     */
    boolean isOverflow();

    /**
     * 策略名.
     */
    String getName();

    /**
     * 累计方法调用总数.
     */
    int addUpTotal();

    /**
     * 累计方法出错总数.
     */
    int addUpError();

}
