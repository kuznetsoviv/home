package ru.kuznetsoviv.parallel.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 3 основные реализации ExecutorService:
 * 1) ThreadPoolExecutor
 * 2) ForkJoinPool
 * 3) ScheduledThreadExecutor
 * <p>
 * Смысл ExecutorService - это управление количеством выделяемых потоков на исполнение задач, типы:
 * 1) CachedThreadPool - всегда создает поток на задачу, если нет свободных в пуле
 * 2) FixedThreadPool - фиксированное количество потоков на выполнение задач
 * 3) SingleThreadPool - один поток на все задачи
 * 4) WorkStealingThreadPool - количество потоков равно количеству ядер процессора
 *
 * При создании пула потоков можно передать фабрику создания потоков (в которой можно делать кастомизированные потоки).
 *
 */
public class Main {

    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 30; i++) {
            es.submit(new MyRunnable());
        }
    }

}
