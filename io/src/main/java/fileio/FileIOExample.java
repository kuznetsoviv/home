package fileio;

import java.io.*;

/**
 * Пример использования файловых потоков для чтения/записи файлов.
 */
public class FileIOExample {

    public static void main(String[] args) {
        // copyFileWithIo();
        // readFileWithFileReader();
        readFileWithBufferedReader();
    }

    /**
     * При чтении файла всегда следует использовать метод с read, в который передавать буффер.
     * Это значительно ускоряет вычитывание данных (так как ОС будет вычитывать данные не по одному байту,
     * а сразу большим количестовом).
     */
    private static void copyFileWithIo() {
        String readPath = FileIOExample.class.getResource("/read.txt").getPath();
        String writePath = FileIOExample.class.getResource("/write.txt").getPath();
        try (InputStream is = new FileInputStream(readPath); OutputStream os = new FileOutputStream(writePath)) {
            byte[] buffer = new byte[4096];
            int r = is.read(buffer);
            while (r != -1) {
                os.write(buffer, 0, r);
                r = is.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Пример чтения файла с использованием класса FileReader.
     */
    private static void readFileWithFileReader() {
        String readPath = FileIOExample.class.getResource("/read.txt").getPath();
        try (FileReader fileReader = new FileReader(readPath)) {
            char[] charBuffer = new char[4096];
            int r = fileReader.read(charBuffer);
            while (r != -1) {
                for (int i = 0; i < r; i++) {
                    System.out.print(charBuffer[i]);
                }
                r = fileReader.read(charBuffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Пример чтения файла м помощью BufferedReader - позволяет считывать файл по-строчно.
     */
    private static void readFileWithBufferedReader() {
        String readPath = FileIOExample.class.getResource("/read.txt").getPath();
        try (FileInputStream fis = new FileInputStream(readPath);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                System.out.println(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
