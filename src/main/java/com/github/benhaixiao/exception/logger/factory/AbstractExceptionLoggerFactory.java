package com.github.benhaixiao.exception.logger.factory;

import com.github.benhaixiao.exception.logger.ExceptionLogger;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author xiaobenhai
 */
public abstract class AbstractExceptionLoggerFactory implements ExceptionLoggerFactory {

    private Map<String, ExceptionLogger> loggerMap = Maps.newConcurrentMap();

    @Override
    public synchronized ExceptionLogger get(String key) {
        ExceptionLogger exceptionLogger = getExceptionLogger(key);
        if (exceptionLogger != null) {
            return exceptionLogger;
        }

        exceptionLogger = build();
        setExceptionLogger(key, exceptionLogger);

        return exceptionLogger;
    }


    @Override
    public ExceptionLogger getAndAddTotal(String key) {
        ExceptionLogger exceptionLogger = get(key);
        exceptionLogger.addUpTotal();
        return exceptionLogger;
    }

    protected abstract ExceptionLogger build();

    private ExceptionLogger getExceptionLogger(String key) {
        ExceptionLogger logger = loggerMap.get(key);
        return logger;
    }

    private void setExceptionLogger(String key, ExceptionLogger exceptionLogger) {
        if (loggerMap.containsKey(key)) {
            throw new IllegalArgumentException("当前的key已有实例化!");
        }
        loggerMap.put(key, exceptionLogger);
    }

}
