package pipeioexample;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Пример использования pipes, которые используются для коммуникации между двумя потоками в рамках одной jvm.
 */
public class PipeIOExample {

    public static void main(String[] args) throws IOException {

        PipedOutputStream outputStream = new PipedOutputStream();
        PipedInputStream inputStream = new PipedInputStream(outputStream);

        Thread thread1 = new Thread(() -> {
            try {
                outputStream.write("Hello world, pipe!".getBytes());
                outputStream.close();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                int data = inputStream.read();
                while (data != -1) {
                    System.out.print((char) data);
                    data = inputStream.read();
                }
                inputStream.close();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        });

        thread1.start();
        thread2.start();

    }

}
