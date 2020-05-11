package ru.kuznetsoviv.printio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * PrintStream используется для форматированного вывода данных.
 * Можно использовать метод printf в си-подобном стиле.
 */
public class PrintIOExample {

    public static void main(String[] args) {
        String writePath = PrintIOExample.class.getResource("/write.txt").getPath();
        try (FileOutputStream outputStream = new FileOutputStream(writePath);
             PrintStream stream = new PrintStream(outputStream)) {
            stream.print(4);
            stream.println("number");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
