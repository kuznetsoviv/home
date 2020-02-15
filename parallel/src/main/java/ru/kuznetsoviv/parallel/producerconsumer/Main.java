package ru.kuznetsoviv.parallel.producerconsumer;

public class Main {

    /**
     * Пример решения задачи consumer/producer с использованием wait/notify.
     * wait - говорит потоку сбросить монитор объекта, который он занял и ожидать пока другой поток не выполнит команду notify
     * запрещает другим потокам использовать этот метод
     * notify - сказать произвольному потоку, который был остановлен в секции wait, что он может начать борьбу за ресурс
     * notifyAll - сказать всем потокам, которые были остановлены в секции wait, что они могут начать борьбу за ресурс
     *
     * @param args аргументы командной строки.
     */
    public static void main(String[] args) {
        Resource resource = new Resource();
        Thread consumerThread = new Thread(new Consumer(resource));
        Thread producerThread = new Thread(new Producer(resource));
        consumerThread.start();
        producerThread.start();
    }
}
