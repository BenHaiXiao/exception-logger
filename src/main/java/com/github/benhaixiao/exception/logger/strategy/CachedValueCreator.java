package com.github.benhaixiao.exception.logger.strategy;

import java.util.concurrent.Callable;

/**
 * @author xiaobenhai
 */
public class CachedValueCreator implements Callable<CachedValue> {

    @Override
    public CachedValue call() throws Exception {
        return new CachedValue();
    }

}
