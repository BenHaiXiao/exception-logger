package com.github.benhaixiao.exception.logger.filter;

import java.util.Arrays;
import java.util.List;

import ch.qos.logback.classic.filter.ThresholdFilter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.spi.FilterReply;

/**
 * Created by Administrator on 2017/3/7.
 */
public class IgnoreExceptionThresholdFilter extends ThresholdFilter {

    List<String> ignoreException;

    @Override
    public FilterReply decide(ILoggingEvent event) {
        FilterReply filterReply = super.decide(event);
        if (null != event.getThrowableProxy() && (ignoreException.contains(event.getThrowableProxy().getClassName())
                                                  || ignoreException.contains(event.getThrowableProxy().getCause().getClassName()))) {
            filterReply = FilterReply.DENY;
        }
        return filterReply;
    }

    public void setIgnoreException(String ignoreException) {
        this.ignoreException = Arrays.asList(ignoreException.split(";"));
    }
}
