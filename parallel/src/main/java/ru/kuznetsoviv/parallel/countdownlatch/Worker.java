package ru.kuznetsoviv.parallel.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class Worker implements Runnable {

    private CountDownLatch startSignal;
    private CountDownLatch doneSignal;

    public Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }

    @Override
    public void run() {
        try {
            startSignal.await();
            System.out.println("worker run");
            doneSignal.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
