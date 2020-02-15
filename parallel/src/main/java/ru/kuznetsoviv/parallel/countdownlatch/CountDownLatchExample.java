package ru.kuznetsoviv.parallel.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * Позволяет задать барьер (значение барьера передается в конструкторе).
 * Поток будет ожидать выполнения пока счетчик барьера не будет равен 0.
 */
public class CountDownLatchExample {

    private static final int COUNT = 5;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(COUNT);
        for (int i = 0; i < COUNT; i++) {
            new Thread(new Worker(startSignal, doneSignal)).start();
        }
        doSomeThing(1);
        startSignal.countDown();
        doneSignal.await();
        doSomeThing(2);

    }

    private static void doSomeThing(int phase) {
        try {
            Thread.sleep(2000);
            System.out.println("doSomeThing is DONE: " + phase);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
