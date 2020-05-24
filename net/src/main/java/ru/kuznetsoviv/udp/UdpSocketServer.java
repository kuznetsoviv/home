package ru.kuznetsoviv.udp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class UdpSocketServer extends Thread {

    private DatagramSocket socket;
    private BufferedReader in;
    private boolean moreQuotes = true;

    public static void main(String[] args) throws IOException {
        System.setProperty("java.net.preferIPv4Stack", "true");
        new UdpSocketServer().start();
    }

    private UdpSocketServer() throws IOException {
        socket = new DatagramSocket(4445);
        try {
            in = new BufferedReader(new FileReader(UdpSocketServer.class.getResource("/one-lines.txt").getPath()));
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void run() {
        while (moreQuotes) {
            try {
                byte[] buf = new byte[256];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String dString;
                if (in == null) {
                    dString = new Date().toString();
                } else {
                    dString = getNextQuote();
                }

                buf = dString.getBytes();

                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                System.out.println("Port: " + port);
                packet = new DatagramPacket(buf, buf.length, address, port);
                socket.send(packet);
            } catch (Exception e) {
                e.printStackTrace();
                moreQuotes = false;
            }
        }
        socket.close();
    }

    private String getNextQuote() {
        String returnValue;
        try {
            if ((returnValue = in.readLine()) == null) {
                in.close();
                moreQuotes = false;
                returnValue = "No more quotes. Goodbye.";
            }
        } catch (IOException e) {
            returnValue = "IOException occurred in server.";
        }
        return returnValue;
    }

}
