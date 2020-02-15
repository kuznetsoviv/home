package ru.kuznetsoviv.parallel.readwritelock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadThread implements Runnable {

    private Message message;
    private ReentrantReadWriteLock lock;

    ReadThread(ReentrantReadWriteLock lock, Message message) {
        this.lock = lock;
        this.message = message;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            if (lock.isWriteLocked()) {
                System.out.println("Lock for Writing");
            }
            try {
                lock.readLock().lock();
                Thread.sleep(1000);
                System.out.println("ReadThread " + Thread.currentThread().getId() + " ----> Message " + message.getMessage());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.readLock().unlock();
            }
        }
    }

}
