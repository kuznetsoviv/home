package ru.kuznetsoviv.service.impl;

import ru.kuznetsoviv.service.api.HelloService;

/**
 * Реализация первого сервиса.
 */
public class HelloServiceOne implements HelloService {

    @Override
    public void sayHello() {
        System.out.println("Hello from ONE service");
    }

    @Override
    public String getName() {
        return "ONE";
    }

}
