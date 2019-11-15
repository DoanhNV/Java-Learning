package doanhnv.socket;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class SocketClient1 {

    public static void main(String[] args) throws IOException {

        Socket client = new Socket("localhost",6666);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
             BufferedReader inputer = new BufferedReader(new InputStreamReader(System.in));) {

            new Thread(() -> {
                    try {
                        String serverResponse = null;
                        while (true) {
                            serverResponse = reader.readLine();
                            if (serverResponse == null) {
                                System.out.println("Messgage from server is Null!");
                                throw new SocketException("Disconect with server!");
                            }
                            System.out.println("Server response: " + serverResponse);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            inputer.close();
                            writer.close();
                            client.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            }).start();

            while (true) {
                System.out.print("You: ");
                String content = inputer.readLine();
                writer.write(content);
                writer.newLine();
                writer.flush();

//                Test disConnect
//                String myMessage = null;
//                writer.write(myMessage);
//                writer.newLine();
//                writer.flush();
            }
        } catch (Exception e) {
            System.out.println("Socket is: " + client.isClosed());
        }
    }
}
