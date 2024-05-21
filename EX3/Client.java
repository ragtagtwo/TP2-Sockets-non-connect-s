import java.net.*;

public class Client {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        
        try {
            // Création du socket UDP
            socket = new DatagramSocket();
            
            // Adresse IP et port du serveur
            InetAddress serverAddress = InetAddress.getByName("localhost"); // Utilisation de localhost, à modifier si nécessaire
            int serverPort = 1250;
            
            // Création du datagramme à envoyer (vide dans cet exemple)
            byte[] requestData = new byte[0]; // Pas de données à envoyer
            
            // Envoi du datagramme au serveur
            DatagramPacket requestPacket = new DatagramPacket(requestData, requestData.length, serverAddress, serverPort);
            socket.send(requestPacket);
            
            // Réception de la réponse du serveur
            byte[] responseData = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length);
            socket.receive(responsePacket);
            
            // Conversion des données reçues en chaîne de caractères
            String dateTime = new String(responsePacket.getData(), 0, responsePacket.getLength());
            
            // Affichage de la date et de l'heure reçues du serveur
            System.out.println("Date et heure reçues du serveur : " + dateTime);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}