package doanhnv.socket;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class SocketClient1 {
    public static int responseStatus;

    public static void main(String[] args) throws IOException {

        Socket client = new Socket("localhost",6666);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
             BufferedReader inputer = new BufferedReader(new InputStreamReader(System.in));) {

            new Thread(() -> {
                    try {
                        String serverResponse = null;
                        while (true) {
                            if (responseStatus == 1) {
                                System.out.println("Thread responseStatus ======================================================= 1");
                                serverResponse = reader.readLine();
                                if (serverResponse == null) {
                                    System.out.println("Messgage from server is Null!");
                                    throw new SocketException("Disconect with server!");
                                }
                                System.out.println("Server response: " + serverResponse);
                                responseStatus = 2;
                            }

                            //System.out.print(""); - Magic line
                        }
                    } catch (Exception e) {
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
                System.out.println("Main 2 responseStatus: " + responseStatus);
                if (responseStatus == 0 || responseStatus == 2) {
                    System.out.print("You: ");
                    String content = inputer.readLine();
                    writer.write(content);
                    writer.newLine();
                    writer.flush();
                    responseStatus = 1;
                    System.out.println("Main responseStatus: " + responseStatus);
                }
//                Test disConnect
//                String myMessage = null;
//                writer.write(myMessage);
//                writer.newLine();
//                writer.flush();
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println("Socket is: " + client.isClosed());
        }
    }
}
