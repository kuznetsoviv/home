package ru.kuznetsoviv.service.impl;

import ru.kuznetsoviv.service.api.HelloService;

/**
 * Реализация второго сервиса.
 */
public class HelloServiceTwo implements HelloService {

    @Override
    public void sayHello() {
        System.out.println("Hello from TWO service");
    }

    @Override
    public String getName() {
        return "TWO";
    }

}
