package sequenceioexample;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * SequenceInputStream - используется для объединения нескольких потоков в один.
 */
public class SequenceIOExample {

    public static void main(String[] args) throws FileNotFoundException {
        String readPath1 = SequenceIOExample.class.getResource("/read.txt").getPath();
        String readPath2 = SequenceIOExample.class.getResource("/additional_read.txt").getPath();
        InputStream inputStream1 = new FileInputStream(readPath1);
        InputStream inputStream2 = new FileInputStream(readPath2);
        List<Byte> result = new ArrayList<>();
        try (InputStream sequenceInputStream = new SequenceInputStream(inputStream1, inputStream2)) {
            byte[] buffer = new byte[1024];
            int size = sequenceInputStream.read(buffer);
            while (size != -1) {
                for (int i = 0; i < size; i++) {
                    result.add(buffer[i]);
                }
                size = sequenceInputStream.read(buffer);
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        byte[] container = new byte[result.size()];
        for (int i = 0; i < result.size(); i++) {
            container[i] = result.get(i);
        }
        System.out.println(new String(container));
    }

}
