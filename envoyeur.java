import java.io.*;
import java.net.*;

public class envoyeur {

    public static void main(String[] args) {
        String hostName = "224.0.0.1";
        int portNumber = 12345;
        String fileName = "test.txt";

        try (
            FileInputStream fileInputStream = new FileInputStream(fileName);
            MulticastSocket socket = new MulticastSocket();
        ) {
            InetAddress group = InetAddress.getByName(hostName);

            byte[] sendBuffer = new byte[1024];

            int bytesRead = 0;
            while ((bytesRead = fileInputStream.read(sendBuffer)) != -1) {
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, bytesRead, group, portNumber);
                socket.send(sendPacket);
            }
            System.out.println("File sent successfully.");
        } catch (IOException e) {
            System.err.println("Error sending file: " + e.getMessage());
        }
    }

}