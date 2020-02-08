package ru.kuznetsoviv.parallel.runnable;

import java.util.concurrent.TimeUnit;

public class MyThread extends Thread {

    @Override
    public void run() {
        try {
            System.out.println("Start Thread: " + getId());
            TimeUnit.SECONDS.sleep(2);
            System.out.println("Stop Thread: " + getId());
        } catch (InterruptedException e) {
            e.printStackTrace(System.out);
        }
    }
}
