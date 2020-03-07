package ru.kuznetsoviv.nio.scattergather;

import java.nio.ByteBuffer;

/**
 * NIO поддерживает возможность записи из канала в разные буферы.
 * Фактически данные в эти буферы будут записаны по-очереди.
 * Не подходит для динамически опеределяемых частей сообщения.
 * --------------------------------------------------------------
 * NIO поддерживает возможность записи из разных буферов в один канал.
 * Фактически данные в канал будут записаны по-очереди.
 * Но если в буфере размером 128 записано только 68 байт, то в канал будет записано 68 байт
 * и дальше будет происходить запись из следующего буфера.
 * Поэтому подходит для динамически опеределяемых частей сообщения.
 */
public class ScatterGatherNIO {

    public static void main(String[] args) {
        /*
         * scattering
        ByteBuffer header = ByteBuffer.allocate(128);
        ByteBuffer body   = ByteBuffer.allocate(1024);

        ByteBuffer[] bufferArray = { header, body };

        channel.read(bufferArray);
         */

        /*
         * gathering
        ByteBuffer header = ByteBuffer.allocate(128);
        ByteBuffer body   = ByteBuffer.allocate(1024);
        //write data into buffers
        ByteBuffer[] bufferArray = { header, body };
        channel.write(bufferArray);
         */

    }

}
