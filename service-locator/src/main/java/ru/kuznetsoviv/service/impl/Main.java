package ru.kuznetsoviv.service.impl;

import ru.kuznetsoviv.service.api.HelloService;

/**
 * Класс для запуска примеров.
 * Сервис локатор используется в DriverManager для jdbc.
 * SPI - service provider interface.
 */
public class Main {

    public static void main(String[] args) {
        HelloService helloOne = HelloService.getInstance("ONE");
        helloOne.sayHello();
        HelloService helloTwo = HelloService.getInstance("TWO");
        helloTwo.sayHello();
    }

}
