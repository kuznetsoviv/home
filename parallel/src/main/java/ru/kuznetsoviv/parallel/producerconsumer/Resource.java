package ru.kuznetsoviv.parallel.producerconsumer;

class Resource {

    static final int COUNT = 5;

    private int value;
    private boolean busy = false;

    synchronized int getValue() {
        if (!busy) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("GET: " + value);
        busy = false;
        notifyAll();
        return value;
    }

    synchronized void setValue(int value) {
        if (busy) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        busy = true;
        System.out.println("PUT: " + value);
        this.value = value;
        notifyAll();
    }
}
