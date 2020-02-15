package ru.kuznetsoviv.parallel.producerconsumer;

public class Consumer implements Runnable {

    private Resource resource;

    Consumer(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 0; i < Resource.COUNT; i++) {
            resource.getValue();
        }
    }

}
