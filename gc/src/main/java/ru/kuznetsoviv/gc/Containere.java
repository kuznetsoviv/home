package ru.kuznetsoviv.gc;

public class Containere {
    private byte[] objects;

    public Containere(byte[] objects) {
        this.objects = objects;
    }

    public void setObjects(byte[] objects) {
        this.objects = objects;
    }

    public byte[] getObjects() {
        return objects;
    }
}
