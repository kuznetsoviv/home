package ru.kuznetsoviv.parallel.thread;

import java.util.concurrent.ThreadFactory;

/**
 * Фабрика потоков используется для создания потоков. В создаваемых объектах можно определить какие-либо настройки.
 */
public class MyThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r);
    }
}
