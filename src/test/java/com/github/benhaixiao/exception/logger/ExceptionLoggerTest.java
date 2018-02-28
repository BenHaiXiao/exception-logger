package com.github.benhaixiao.exception.logger;

import com.github.benhaixiao.exception.logger.ExceptionLogger;
import com.github.benhaixiao.exception.logger.strategy.FrequencyStrategy;
import com.github.benhaixiao.exception.logger.strategy.RatioStrategy;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author xiaobenhai
 */
public class ExceptionLoggerTest {

    @Test
    public void testAddStrategy() throws Exception {
        ExceptionLogger logger1 = new ExceptionLogger(null);
        assertEquals("", logger1.getStrategyName());
        logger1.addStrategy(new RatioStrategy(1, 3));
        assertEquals("ratio-strategy", logger1.getStrategyName());
        logger1.addStrategy(new FrequencyStrategy(1, 3));
        assertEquals("ratio-strategy-and-frequency-strategy", logger1.getStrategyName());

        ExceptionLogger logger2 = new ExceptionLogger(new RatioStrategy(1, 3));
        assertEquals("ratio-strategy", logger2.getStrategyName());
        logger2.addStrategy(new FrequencyStrategy(1, 3));
        assertEquals("ratio-strategy-and-frequency-strategy", logger2.getStrategyName());
    }

    @Test
    public void testError() throws Exception {

    }

    @Test
    public void testAddUpTotal() throws Exception {
        ExceptionLogger logger1 = new ExceptionLogger(null);
        assertEquals(0, logger1.addUpTotal());
        assertEquals(0, logger1.addUpTotal());

        logger1.addStrategy(new RatioStrategy(1, 3));
        assertEquals(1, logger1.addUpTotal());
        assertEquals(2, logger1.addUpTotal());
    }

}