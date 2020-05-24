package ru.kuznetsoviv.client;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Пример создания TCP-соединения и отправки при помощи него сообщения HTTP протокола.
 * Клиент для удаленной программы.
 */
public class ClientSocketExample {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("java-course.ru", 80);
        // времяч в течении которого сокет ожидает каких-либо данных
        socket.setSoTimeout(2000);

        OutputStream os = socket.getOutputStream();
        String command = "GET /haiku.html HTTP/1.1" + System.lineSeparator() +
                "Host: java-course.ru" + System.lineSeparator() +
                "Connection: close" + System.lineSeparator() +
                System.lineSeparator();
        os.write(command.getBytes());

        InputStream is = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "cp1251"));
        String line = bufferedReader.readLine();
        while (line != null) {
            System.out.println(line);
            try {
                line = bufferedReader.readLine();
            } catch (SocketTimeoutException e) {
                // вызовется, если данные не придут в течении 2-х секунд (указано выше)
                e.printStackTrace(System.out);
            }
        }
        socket.close();
    }

}

