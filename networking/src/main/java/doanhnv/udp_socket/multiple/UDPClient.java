package doanhnv.udp_socket.multiple;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class UDPClient {
    static final String MESSAGE_GROUP_ADDRESS = "230.0.0.1";
    static final int MESSAGE_GROUP_PORT = 4446;

    public static void main(String[] args) throws IOException {
        MulticastSocket multicastSocket = new MulticastSocket(MESSAGE_GROUP_PORT);
        multicastSocket.joinGroup(InetAddress.getByName(MESSAGE_GROUP_ADDRESS));

        byte[] buffer = new byte[256];
        DatagramPacket notification = new DatagramPacket(buffer, buffer.length);

        while (true) {
            multicastSocket.receive(notification);
            System.out.println("Server: " + new String(notification.getData()));
        }
    }
}
