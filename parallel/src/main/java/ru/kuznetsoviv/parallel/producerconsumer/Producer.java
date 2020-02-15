package ru.kuznetsoviv.parallel.producerconsumer;

public class Producer implements Runnable {

    private Resource resource;

    Producer(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 0; i < Resource.COUNT; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.setValue(i);
        }
    }

}
