package ru.kuznetsoviv.dynamic.method.factory;

/**
 * Базовая точка для запуска примера с поиском и вызовом динамического метода.
 */
public class PasswordGeneratorExample {

    public static void main(String[] args) {
        System.out.println(PasswordGeneratorFactory.getPassword());
    }

}
