package doanhnv.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class SocketServerHandler {

    public static void main(String[] args) throws IOException {
        final int port = 6666;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server socket waiting connection...");

        while (true) {
            Socket connectedSocket = serverSocket.accept();
            System.out.println("Accept connection from " + connectedSocket.getPort());

            new Thread(() -> {
                System.out.println("Start new thread handle socket!");
                try (BufferedOutputStream writer = new BufferedOutputStream(connectedSocket.getOutputStream());
                     BufferedReader reader = new BufferedReader(new InputStreamReader(connectedSocket.getInputStream()))) {

                    String clientMessage = "";
                    while (true) {
                        clientMessage = reader.readLine();
                        if (clientMessage == null) {
                            throw new SocketException("Socket is disconected: " + " - " + connectedSocket.getRemoteSocketAddress());
                        }
                        System.out.println("clientMessage: " + clientMessage);

                        String response = "received: " + clientMessage  + "\n";
                        writer.write(response.getBytes());
                        writer.flush();
                        System.out.println("Response to client success!");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }


}
