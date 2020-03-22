package memoryreadwriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Для работы с массивом байт есть объекты обертки стримов.
 */
public class MemoryIOExample {

    public static void main(String[] args) {
        byte[] data = new byte[10];
        data[0] = 2;
        data[1] = 3;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(2);
        outputStream.toByteArray();
    }

}
