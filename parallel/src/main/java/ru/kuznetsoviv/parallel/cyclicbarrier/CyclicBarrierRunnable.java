package ru.kuznetsoviv.parallel.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierRunnable implements Runnable {

    private CyclicBarrier barrier1;
    private CyclicBarrier barrier2;

    public CyclicBarrierRunnable(CyclicBarrier barrier1, CyclicBarrier barrier2) {
        this.barrier1 = barrier1;
        this.barrier2 = barrier2;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " start 1");
            Thread.sleep(1000 + (int) (Math.random() * 4000));
            System.out.println(Thread.currentThread().getName() + " waiting at barrier 1");
            this.barrier1.await();

            System.out.println(Thread.currentThread().getName() + " start 2");
            Thread.sleep(1000 + (int) (Math.random() * 4000));
            System.out.println(Thread.currentThread().getName() + " waiting at barrier 2");
            this.barrier2.await();

            System.out.println(Thread.currentThread().getName() + " done");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

    }

}
