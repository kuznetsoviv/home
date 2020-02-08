package ru.kuznetsoviv.parallel.callable;

import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * Класс объекта, запускаемого в отдельном потоке с возвращаемым результатом.
 */
public class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        return UUID.randomUUID().toString();
    }

}
