package ru.kuznetsoviv.parallel.executor;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class MyInterruptingThread implements Runnable {

    private Future<Integer> future;

    public MyInterruptingThread(Future<Integer> future) {
        this.future = future;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
            future.cancel(true);
        } catch (InterruptedException e) {
            e.printStackTrace(System.out);
            Thread.currentThread().interrupt();
        }
    }

}
