package ru.kuznetsoviv.parallel.callable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Класс для проверки выполнения callable-фунции.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask<String> task1 = new FutureTask<>(new MyCallable());
        FutureTask<String> task2 = new FutureTask<>(new MyCallable());

        new Thread(task1).start();
        new Thread(task2).start();

        System.out.println(task1.get());
        System.out.println(task2.get());

    }
}
