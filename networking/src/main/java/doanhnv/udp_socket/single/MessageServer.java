package doanhnv.udp_socket.single;

import java.io.IOException;
import java.net.*;

public class MessageServer {

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(1111, InetAddress.getByName("localhost"));

        System.out.println("Ready for messaging at " + socket.getLocalPort());
        while (true) {
            byte[] buffer = new byte[2048];
            DatagramPacket receivePackage = new DatagramPacket(buffer, buffer.length);
            socket.receive(receivePackage);
            System.out.println(receivePackage.getAddress());
            String message = "Client messsage: " + new String(receivePackage.getData()).trim() + " from " + receivePackage.getAddress() + ":" + receivePackage.getPort();
            System.out.println(message);
        }
    }
}
