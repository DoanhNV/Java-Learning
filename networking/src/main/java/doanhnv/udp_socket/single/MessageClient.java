package doanhnv.udp_socket.single;

import java.io.IOException;
import java.net.*;

public class MessageClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        final InetAddress myAddress = InetAddress.getByName("localhost");
        DatagramSocket socket = new DatagramSocket(2222, myAddress);

        final InetAddress serverHost = InetAddress.getLocalHost();
        final int serverPort = 1111;

        while(true) {
            byte[] buffer = "Hello Doanh".getBytes();
            DatagramPacket  message = new DatagramPacket(buffer, buffer.length, myAddress, serverPort);
            socket.send(message);
            System.out.println("Sent pakcage: " + message);
            Thread.sleep(1000);
        }
    }
}
