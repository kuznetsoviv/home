package ru.kuznetsoviv.parallel.runnable;

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
