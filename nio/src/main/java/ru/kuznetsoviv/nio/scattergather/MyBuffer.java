package ru.kuznetsoviv.nio.scattergather;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * Пример реализации scatter (write-метод) и gather (read-метод) работы NIO.
 */
public class MyBuffer implements SeekableByteChannel, GatheringByteChannel, ScatteringByteChannel {

    private List<Byte> readableContent = new ArrayList<>();
    private int readablePosition = 0;

    {
        for (int i = 0; i < 2000; i++) {
            readableContent.add((byte) i);
        }
    }

    private List<Byte> writableContent = new ArrayList<>();
    private int writablePosition = 0;

    @Override
    public long write(ByteBuffer[] srcs, int offset, int length) throws IOException {
        return 0;
    }

    @Override
    public long write(ByteBuffer[] srcs) throws IOException {
        for (ByteBuffer dst : srcs) {
            dst.flip();
            while (dst.hasRemaining()) {
                if (writablePosition >= writableContent.size()) {
                    break;
                }
                dst.put(writableContent.get(writablePosition));
                writablePosition++;
            }
        }
        return 0;
    }

    @Override
    public long read(ByteBuffer[] dsts, int offset, int length) throws IOException {
        return 0;
    }

    @Override
    public long read(ByteBuffer[] dsts) throws IOException {
        for (ByteBuffer dst : dsts) {
            while (dst.hasRemaining()) {
                if (readablePosition >= readableContent.size()) {
                    break;
                }
                dst.put(readableContent.get(readablePosition));
                readablePosition++;
            }
        }
        return 0;
    }

    @Override
    public int read(ByteBuffer dst) throws IOException {
        return 0;
    }

    @Override
    public int write(ByteBuffer src) throws IOException {
        return 0;
    }

    @Override
    public long position() throws IOException {
        return 0;
    }

    @Override
    public SeekableByteChannel position(long newPosition) throws IOException {
        return null;
    }

    @Override
    public long size() throws IOException {
        return 0;
    }

    @Override
    public SeekableByteChannel truncate(long size) throws IOException {
        return null;
    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public void close() throws IOException {

    }
}
