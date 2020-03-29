package fileclass;

import java.io.File;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Демонстрация работы класса File.
 */
public class FileExample {

    public static void main(String[] args) {
        File file = new File(FileExample.class.getResource("/read.txt").getPath());
        System.out.println("exists " + file.exists());
        System.out.println("file can execute " + file.canExecute());
        System.out.println("file can write " + file.canWrite());
        System.out.println("file absolute path " + file.getAbsolutePath());
        // --------------------------------------------
        // Переименовать файл или переместить файл
        // file.renameTo(new File("<file path>/<file name>"));
        // --------------------------------------------
        // Удалить файл при выходе из программы
        // file.deleteOnExit();
        // --------------------------------------------
        // Пример создания сразу списка директорий
        // File dirs = new File("5//6//f//3");
        // dirs.mkdirs();
        // --------------------------------------------
        // Удаление файла - может удалить только файл или пустую директорию, для удаления всех директорий надо использовать
        // рекурсивное удаление
        // file.delete()
        System.out.println("Пример получения файлов из директроии: ");
        File currentFile = new File(".");
        Stream.of(Objects.requireNonNull(currentFile.list((dir, name) -> name.equals("pom.xml")))).forEach(System.out::println);

    }
}
