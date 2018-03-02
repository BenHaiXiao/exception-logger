package com.github.benhaixiao.exception.logger.strategy;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author xiaobenhai
 */
public class PeriodStrategyTest {

    private PeriodStrategy strategy;

    @Before
    public void before() {
        strategy = new PeriodStrategy(5, 3);
    }

    @Test
    public void testIsOverflow() {
        strategy.addUpTotal();
        strategy.addUpTotal();
        strategy.addUpTotal();
        strategy.addUpError();
        strategy.addUpTotal();
        strategy.addUpError();
        assertTrue(!strategy.isOverflow());
        strategy.addUpTotal();
        strategy.addUpError();
        assertTrue(strategy.isOverflow());
        strategy.addUpTotal();
        strategy.addUpError();
        assertTrue(!strategy.isOverflow());
        strategy.addUpTotal();
        strategy.addUpError();
        assertTrue(!strategy.isOverflow());
        strategy.addUpTotal();
        strategy.addUpError();
        assertTrue(strategy.isOverflow());
        strategy.addUpTotal();
        assertTrue(strategy.isOverflow());
    }

    @Test
    public void testAddUp() {
        assertEquals(1, strategy.addUpTotal());
        assertEquals(2, strategy.addUpTotal());
        assertEquals(3, strategy.addUpTotal());
        assertEquals(4, strategy.addUpTotal());
        assertEquals(5, strategy.addUpTotal());
        assertEquals(1, strategy.addUpTotal());
        assertEquals(2, strategy.addUpTotal());
        assertEquals(3, strategy.addUpTotal());
    }
}
