package pushbackio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;

/**
 * PushBack поток используется для того, чтобы была возможность прочитать символ и вернуться обратно.
 */
public class PushBackIOExample {

    public static void main(String[] args) {
        String readPath = PushBackIOExample.class.getResource("/read.txt").getPath();
        try (PushbackReader reader = new PushbackReader(new BufferedReader(new FileReader(readPath)))) {
            int data = reader.read();
            // возвращаемся назад на один символ
            // по умолчанию можно вернуться только на один символ назад
            reader.unread(data);
            //
            while (data != -1) {
                System.out.print((char) data);
                data = reader.read();
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }

}
