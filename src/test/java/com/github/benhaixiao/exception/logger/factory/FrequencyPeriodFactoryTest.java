package com.github.benhaixiao.exception.logger.factory;

import com.github.benhaixiao.exception.logger.ExceptionLogger;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author xiaobenhai
 */
public class FrequencyPeriodFactoryTest {

    private FrequencyPeriodLoggerFactory factory;

    @Before
    public void before() {
        factory = new FrequencyPeriodLoggerFactory();
        factory.init(5, 4, 5, 3);
    }

    @Test
    public void testGetLogger() throws Exception {
        ExceptionLogger logger1 = factory.get("logger1");
        ExceptionLogger logger2 = factory.get("logger2");
        ExceptionLogger logger3 = factory.get("logger2");

        assertEquals(1, logger1.addUpTotal());
        assertEquals(1, logger2.addUpTotal());
        assertEquals(2, logger3.addUpTotal());
    }

}
