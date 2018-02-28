package com.github.benhaixiao.exception.logger.strategy;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xiaobenhai
 */
public class CachedValue {

    private AtomicInteger total = new AtomicInteger(0);
    private AtomicInteger error = new AtomicInteger(0);

    public int addUpTotal() {
        return total.incrementAndGet();
    }

    public int addUpError() {
        return error.incrementAndGet();
    }

    public int getTotal() {
        return total.get();
    }

    public int getError() {
        return error.get();
    }

    public int getRatio() {
        int total = getTotal();
        if (total == 0) {
            total = 1;
        }
        return getError() * 100 / total;
    }

}
