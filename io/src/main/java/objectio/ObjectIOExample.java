package objectio;

import java.io.*;

/**
 * Пример работы с потоками сериализации/десериализации данных.
 */
public class ObjectIOExample {

    public static void main(String[] args) throws IOException {
        String writePath = ObjectIOExample.class.getResource("/write.txt").getPath();
        Person savedPerson = new Person("Igor", 28);
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(writePath))) {
            objectOutputStream.writeObject(savedPerson);
        }
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(writePath))) {
            Person person = (Person) objectInputStream.readObject();
            System.out.println(person.getName() + " " + person.getAge());
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

}
