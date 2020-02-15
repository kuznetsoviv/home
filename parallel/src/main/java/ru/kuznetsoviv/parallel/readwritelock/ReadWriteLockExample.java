package ru.kuznetsoviv.parallel.readwritelock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Экземпляр read write lock используется для решениея задачи публикация/чтение.
 * То есть в этом экземпляре можно взять lock для чтения (все читальщики могут одновременно читать)
 * или для записи (писать может только один поток).
 */
public class ReadWriteLockExample {

    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        Message message = new Message("My message");

        for (int i = 0; i < 5; i++) {
            new Thread(new ReadThread(lock, message)).start();
        }

        new Thread(new WriteThread(lock, "first", message)).start();
        new Thread(new WriteThread(lock, "second", message)).start();

    }
}
