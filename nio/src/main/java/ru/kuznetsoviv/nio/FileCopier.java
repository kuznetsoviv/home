package ru.kuznetsoviv.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Пример копирования файлов с использованием потоков.
 */
public class FileCopier {

    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream("readFile");
        FileOutputStream outputStream = new FileOutputStream("writeFile");
        FileChannel inChannel = inputStream.getChannel();
        FileChannel outChannel = outputStream.getChannel();
        inChannel.transferTo(0, inChannel.size(), outChannel);
    }
}
