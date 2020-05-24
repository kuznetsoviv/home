package ru.kuznetsoviv.tcp.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Пример класса, реализующего клиентский сокет для тестирования серверного сокета.
 */
public class ClientSocketExample {

    public static void main(String[] args) {
        // в случае большого количества клиентов может вылетать ошибка java.net.SocketException, так сервер их не сможет обработать
        for (int i = 0; i < 10; i++) {
            new ClientSocketThread().start();
        }
    }

    private static class ClientSocketThread extends Thread {

        @Override
        public void run() {
            try (Socket socket = new Socket("127.0.0.1", 9999);
                 PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                pw.println("Hello from client " + Thread.currentThread().getName());
                String line = br.readLine();
                while (line != null) {
                    System.out.println(line);
                    if (line.equals("bye")) {
                        break;
                    }
                    pw.println("bye");
                    line = br.readLine();
                }
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
    }

}
