package ru.kuznetsoviv.parallel.join;

public class Thread1 extends Thread {

    @Override
    public void run() {
        try {
            System.out.println("Thread 1 is started");
            Thread thread2 = new Thread2();
            thread2.start();
            thread2.join();
            System.out.println("Thread 1 is done");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
