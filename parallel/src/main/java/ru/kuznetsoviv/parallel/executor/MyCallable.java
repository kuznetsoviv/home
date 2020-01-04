package ru.kuznetsoviv.parallel.executor;

import java.util.concurrent.Callable;

/**
 * Задача с возвращаемым результатом.
 * Происходит имитация задержки в 5 секунд.
 */
public class MyCallable implements Callable<Integer> {

    @Override
    public Integer call() {
        try {
            System.out.println("Started: " + Thread.currentThread().getId());
            Thread.sleep(1);
            long d1 = System.currentTimeMillis();
            long d2 = System.currentTimeMillis();
            while (d2 < d1 + 3000) {
                d2 = System.currentTimeMillis();
            }
            System.out.println("Finished: " + Thread.currentThread().getId());
        } catch (InterruptedException e) {
            e.printStackTrace(System.out);
            Thread.currentThread().interrupt();
        }
        return 99;
    }

}
