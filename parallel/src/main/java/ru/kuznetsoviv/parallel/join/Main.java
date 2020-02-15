package ru.kuznetsoviv.parallel.join;

/**
 * Пример использования функции join в потоках.
 * Заставляет ждать, пока не выполниться другой поток.
 */
public class Main {

    public static void main(String[] args) {
        Thread thread1 = new Thread1();
        thread1.start();
    }

}
