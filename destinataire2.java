import java.io.*;
import java.net.*;

public class destinataire2 {

    public static void main(String[] args) {

        int portNumber = 12345;
        String fileName = "received.txt";
        byte[] receiveBuffer = new byte[1024];

        try {
            MulticastSocket socket = new MulticastSocket(portNumber);
            InetAddress group = InetAddress.getByName("224.0.0.1");
            socket.joinGroup(group);

            FileOutputStream fileOutputStream = new FileOutputStream(fileName);

            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            int bytesRead = 0;
            while (true) {
                socket.receive(receivePacket);
                bytesRead = receivePacket.getLength();
                if (bytesRead == -1) {
                    break;
                }
                fileOutputStream.write(receivePacket.getData(), 0, bytesRead);
            }
            System.out.println("File received successfully.");
        } catch (IOException e) {
            System.err.println("Error receiving file: " + e.getMessage());
        }
    }

}