package ru.kuznetsoviv.gc;

public class Container {
    private byte[] objects;

    public Container(byte[] objects) {
        this.objects = objects;
    }

    public void setObjects(byte[] objects) {
        this.objects = objects;
    }

    public byte[] getObjects() {
        return objects;
    }
}
