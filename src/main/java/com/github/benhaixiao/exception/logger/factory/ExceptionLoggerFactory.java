package com.github.benhaixiao.exception.logger.factory;

import com.github.benhaixiao.exception.logger.ExceptionLogger;

/**
 * @author xiaobenhai
 */
public interface ExceptionLoggerFactory {

    ExceptionLogger get(String key);

    ExceptionLogger getAndAddTotal(String key);

}
