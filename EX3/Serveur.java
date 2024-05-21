import java.net.*;
import java.util.Date;

public class Serveur {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        
        try {
            // Création du socket UDP écoutant sur le port 1250
            socket = new DatagramSocket(1250);
            
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            
            while (true) {
                // Réception du datagramme
                socket.receive(packet);
                
                // Obtention de l'adresse IP et du port de l'émetteur
                InetAddress clientAddress = packet.getAddress();
                int clientPort = packet.getPort();
                
                // Obtention de la date et de l'heure courante
                String dateTime = new Date().toString();
                
                // Conversion de la date et de l'heure en tableau d'octets
                byte[] responseData = dateTime.getBytes();
                
                // Création du datagramme de réponse contenant la date et l'heure
                DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, clientAddress, clientPort);
                
                // Envoi du datagramme de réponse
                socket.send(responsePacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}