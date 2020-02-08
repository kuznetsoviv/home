package ru.kuznetsoviv.parallel.runnable;

public class Main {

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            MyThread myThread = new MyThread();
            myThread.start();
        }

        for (int i = 0; i < 5; i++) {
            Thread myThread = new Thread(new MyRunnable());
            myThread.start();
        }
    }

}
