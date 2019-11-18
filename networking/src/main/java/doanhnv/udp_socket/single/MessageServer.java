package doanhnv.udp_socket.single;

import java.io.IOException;
import java.net.*;

public class MessageServer {

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(1111, InetAddress.getByName("localhost"));

        System.out.println("Ready for messaging at " + socket.getLocalPort());
        while (true) {
            byte[] buffer = new byte[1024];
            DatagramPacket receivePakage = new DatagramPacket(buffer, buffer.length);
            socket.receive(receivePakage);
            System.out.println(receivePakage.getAddress());
            System.out.println("Client messsage: " + new String(receivePakage.getData()) + " from " + receivePakage.getAddress() + ":" + receivePakage.getPort());
        }
    }
}
