package ru.kuznetsoviv.parallel.readwritelock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class WriteThread implements Runnable {

    private String name;
    private ReentrantReadWriteLock lock;
    private Message message;

    WriteThread(ReentrantReadWriteLock lock, String name, Message message) {
        this.lock = lock;
        this.name = name;
        this.message = message;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
                lock.writeLock().lock();
                System.out.println("I take lock for " + name);
                message.setMessage(message.getMessage().concat(name));
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.writeLock().unlock();
            }
        }
    }

}
