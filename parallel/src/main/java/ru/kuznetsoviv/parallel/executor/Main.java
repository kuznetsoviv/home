package ru.kuznetsoviv.parallel.executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
 * <p>
 * При создании пула потоков можно передать фабрику создания потоков (в которой можно делать кастомизированные потоки).
 * <p>
 * В сервис исполнения задач можно отправить задачу на исполнение с помощью метода execute, когда не важен ее результат.
 * Также можно отправить задачу с помощью метода submit, когда необходимо получить результат выполнения.
 * <p>
 * В случае использования метода submit мы получаем объект Future, который можно использовать для получения состояния
 * выполнения задачи и ее результата. Также с помощью него можно отменить выполнение задачи.
 * <p>
 * Все сервисы исполнения задач создаваемые с помощью вспомогательных методов создаются с помощью конструктора ThreadPoolExecutor.
 * Собственно этот класс и отвечает за выполнение поставляемых задач.
 * Поддерживает задание максимального количества потоков и количество потоков, которые будут созданы при создании его экземпляра.
 * Этот класс можно переопределять для реализации дополнительных обработчиков. Например на создание и удаление потока.
 */
public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(5);
        Future<Integer> future = es.submit(new MyCallable());
        es.execute(new MyInterruptingThread(future));
        Integer result = future.get();
        System.out.println(result);
        System.out.println("Shutdown");
        es.shutdown();
    }

}
