package com.github.benhaixiao.exception.logger.demo;

import com.github.benhaixiao.exception.logger.ExceptionLogger;
import com.github.benhaixiao.exception.logger.factory.ExceptionLoggerFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author xiaobenhai
 * sping使用列子
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context-error.xml")
public class LoggerDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerDemo.class);
    @Autowired
    @Qualifier("defaultExceptionLoggerFactory")
    private ExceptionLoggerFactory exceptionLoggerFactory;

    @Test
    public void  test(){
        ExceptionLogger exceptionLogger = exceptionLoggerFactory.getAndAddTotal("key-test");
        try {
            throw new Exception();
        }catch (Exception e){
            if (exceptionLogger.addUpErrorAndIsOverflow()) {
                LOGGER.error("test is print error log ");
            } else {
                LOGGER.warn("test is print warn log");
            }
        }

    }
}
