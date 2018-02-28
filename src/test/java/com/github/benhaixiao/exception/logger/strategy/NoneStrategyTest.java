package com.github.benhaixiao.exception.logger.strategy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author xiaobenhai
 */
public class NoneStrategyTest {

    private NoneStrategy strategy;

    @Before
    public void before() {
        strategy = new NoneStrategy();
    }

    @After
    public void after() {
        strategy = null;
    }

    @Test
    public void testIsOverflow() throws Exception {
        assertTrue(strategy.isOverflow());
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("", strategy.getName());
    }

    @Test
    public void testAddUpTotal() throws Exception {
        strategy.addUpTotal();
        assertEquals(0, strategy.addUpTotal());
    }

    @Test
    public void testAddUpError() throws Exception {
        strategy.addUpError();
        assertEquals(0, strategy.addUpError());
    }
}