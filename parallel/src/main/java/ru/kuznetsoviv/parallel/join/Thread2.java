package ru.kuznetsoviv.parallel.join;

import java.util.concurrent.TimeUnit;

public class Thread2 extends Thread {

    @Override
    public void run() {
        try {
            System.out.println("Thread 2 is started");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Thread 2 is done");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
