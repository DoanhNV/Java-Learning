package doanhnv.udp_socket.multiple;

import java.io.IOException;
import java.net.*;

public class UDPServer {
    static final int SERVER_PORT = 1111;
    static final long A_SECOND = 1000;
    static final String CLIENT_ADDRESS = "230.0.0.1";
    static final int CLIENT_PORT = 4446;


    public static void main(String[] args) throws IOException, InterruptedException {
        DatagramSocket socket = new DatagramSocket(SERVER_PORT);

        byte[] buffer = "You have new message from DoanhNV".getBytes();
        InetAddress clientAddress = InetAddress.getByName(CLIENT_ADDRESS);
        while (true) {
            DatagramPacket notification = new DatagramPacket(buffer, buffer.length, clientAddress, CLIENT_PORT);
            socket.send(notification);
            System.out.println("Sent package: " + buffer);
            Thread.sleep(A_SECOND);
        }
    }
}
