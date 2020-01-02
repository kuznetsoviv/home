package ru.kuznetsoviv.parallel.thread;

import java.util.concurrent.TimeUnit;

public class MyRunnable implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println("Start Runnable: " + Thread.currentThread().getId());
            TimeUnit.SECONDS.sleep(2);
            System.out.println("Stop Runnable: " + Thread.currentThread().getId());
        } catch (InterruptedException e) {
            e.printStackTrace(System.out);
        }
    }

}
