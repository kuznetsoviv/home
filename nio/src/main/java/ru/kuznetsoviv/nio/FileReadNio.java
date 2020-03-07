package ru.kuznetsoviv.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Ключевые понятия nio:
 * 1) Channel - подобно stream, из него данные считываются в буффер, типы:
 * ---FileChannel
 * ---DatagramChannel
 * ---SocketChannel
 * ---ServerSocketChannel
 * Отличия от stream:
 * ---1) можно читать и писать
 * ---2) могут быть прочитаны и записаны асинхронно
 * ---3) читают или пишутся из буфера
 * ----------------------------------------------------------------------------
 * 2) Buffer - сюда записываются данные из канала, также данные отсюда могут записываться в канала, примеры типов:
 * ---ByteBuffer
 * ---CharBuffer
 * ---DoubleBuffer
 * ---FloatBuffer
 * ----------------------------------------------------------------------------
 * Работа с буфером:
 * ---сначала пишем данные в буфер,
 * ---вызываем метод flip - переносим курсор на начало,
 * ---читаем данные из буфера,
 * ---вызываем один из методов clear - полностью очищает буфер, compact - очищает только то, что было прочитано
 * ---далее цикл, если нужен
 * ----------------------------------------------------------------------------
 * ---метод mark используется чтобы отметить определенную позицию в буфере, на которую позже можно будет переместиться с помощью метода reset
 * ----------------------------------------------------------------------------
 * 3) Selector - позволяет обрабатывать несколько каналов одним потоком
 * (полезно, когда есть много открытых каналов, и они имеют низкий траффик, например, чат)
 */
public class FileReadNio {

    public static void main(String[] args) {
        String path = FileReadNio.class.getResource("file.txt").getFile();
        try (RandomAccessFile file = new RandomAccessFile(path, "rw"); FileChannel channel = file.getChannel()) {
            ByteBuffer buf = ByteBuffer.allocate(48);
            int bytesRead = channel.read(buf);
            while (bytesRead != -1) {
                System.out.println("Read " + bytesRead);
                buf.flip();
                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get());
                }
                buf.clear();
                bytesRead = channel.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

}
