package com.github.benhaixiao.exception.logger.strategy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author xiaobenhai
 */
public class RatioStrategyTest {

    private RatioStrategy strategy;

    @Before
    public void before() {
        strategy = new RatioStrategy(2, 50);
    }

    @After
    public void after() {
        strategy = null;
    }

    @Test
    public void testIsOverflow() throws Exception {
        strategy.addUpTotal();

        strategy.addUpError();

        assertTrue(strategy.isOverflow());

        strategy.addUpTotal();

        assertTrue(strategy.isOverflow());

        strategy.addUpTotal();

        assertTrue(!strategy.isOverflow());

        strategy.addUpError();

        assertTrue(strategy.isOverflow());

        Thread.sleep(3 * 1000);

        assertTrue(!strategy.isOverflow());

        strategy.addUpTotal();

        strategy.addUpError();

        assertTrue(strategy.isOverflow());

        strategy.addUpTotal();

        assertTrue(strategy.isOverflow());

        strategy.addUpTotal();

        assertTrue(!strategy.isOverflow());

        strategy.addUpError();

        assertTrue(strategy.isOverflow());
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("ratio-strategy", strategy.getName());
    }

    @Test
    public void testAddUpTotal() throws Exception {
        assertEquals(1, strategy.addUpTotal());
        assertEquals(2, strategy.addUpTotal());
        assertEquals(3, strategy.addUpTotal());

        Thread.sleep(100);

        assertEquals(4, strategy.addUpTotal());
        assertEquals(5, strategy.addUpTotal());
        assertEquals(6, strategy.addUpTotal());

        Thread.sleep(3 * 1000);

        assertEquals(1, strategy.addUpTotal());
    }

    @Test
    public void testAddUpError() throws Exception {
        assertEquals(1, strategy.addUpError());
        assertEquals(2, strategy.addUpError());
        assertEquals(3, strategy.addUpError());

        Thread.sleep(100);

        assertEquals(4, strategy.addUpError());
        assertEquals(5, strategy.addUpError());
        assertEquals(6, strategy.addUpError());

        Thread.sleep(3 * 1000);

        assertEquals(1, strategy.addUpError());
    }

}