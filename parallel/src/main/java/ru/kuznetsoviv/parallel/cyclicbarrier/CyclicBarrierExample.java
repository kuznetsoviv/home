package ru.kuznetsoviv.parallel.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * Барьер используется, как счетчик до которого должно дойти определенное количество потоков,
 * прежде чем они смогут работать далее.
 * Можно в конструктор передать объек Runnable для выполнения каког-либо действия.
 */
public class CyclicBarrierExample {

    public static void main(String[] args) {
        CyclicBarrier barrier1 = new CyclicBarrier(3);
        CyclicBarrier barrier2 = new CyclicBarrier(3);
        new Thread(new CyclicBarrierRunnable(barrier1, barrier2)).start();
        new Thread(new CyclicBarrierRunnable(barrier1, barrier2)).start();
        new Thread(new CyclicBarrierRunnable(barrier1, barrier2)).start();
    }

}
