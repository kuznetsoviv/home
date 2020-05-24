package ru.kuznetsoviv.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Пример простого сервера.
 */
public class ServerSocketExample {

    public static void main(String[] args) throws IOException {
        int port = 9999;
        System.out.println("Try to bind to port: " + port);
        // специальный класс для открытия серверного сокета
        // текущая программа занимает серверный порт 9999
        // по умолчанию сокет может принять только 50 соединений
        // backlog - количество запращиваемых соединений, которые можно принять и поставить в очередь на обработку
        ServerSocket server = new ServerSocket(port);
        System.out.println("Server socket is opened");
        // ждать соединения от клиента
        // функция accept блокирующая, поэтому надо запускать обработку сокета в отдельном потоке
        while (true) {
            Socket socket = server.accept();
            System.out.println("Connection is accepted");
            new SocketThread(socket).start();
        }

    }

    private static class SocketThread extends Thread {

        private Socket socket;

        public SocketThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                System.out.println("Connection is accepted");
                try (PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
                     BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    while ((str = br.readLine()) != null) {
                        System.out.println(str);
                        if (str.equals("bye")) {
                            pw.println("bye");
                            break;
                        } else {
                            pw.println("Server is answering: " + str);
                        }
                    }
                }
                socket.close();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
    }

}
