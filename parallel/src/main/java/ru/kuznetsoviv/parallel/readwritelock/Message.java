package ru.kuznetsoviv.parallel.readwritelock;

class Message {

    private String message;

    Message(String message) {
        this.message = message;
    }

    void setMessage(String message) {
        this.message = message;
    }

    String getMessage() {
        return message;
    }

}
