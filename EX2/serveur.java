import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.*;
import java.util.Scanner;
import f_voiture.Voiture;


public class serveur{
 public static void main(String argv[]) {
 int port = 0;
 Scanner keyb = new Scanner(System.in);
 try {
 
 // on récupère le paramètre : port d'écoute 
 System.out.println("port d'écoute : ");
 port = keyb.nextInt();
 DatagramPacket packet;


 // création d'une socket liée au port précisé en paramètre
 DatagramSocket socket = new DatagramSocket(port);

 // tableau de 100 octets qui contiendra les données reçues
 byte[] buffer = new byte[1024];

 // création d'un paquet en utilisant le tableau d'octets
 packet = new DatagramPacket(buffer, buffer.length);

// attente de la réception d'un paquet. Le paquet reçu est placé 
// dans packet et ses données dans data.
 socket.receive(packet);


// Deserialize the received byte array back into a Voiture object
ByteArrayInputStream bis = new ByteArrayInputStream(packet.getData());
ObjectInputStream ois = new ObjectInputStream(bis);
Voiture v_recu = (Voiture) ois.readObject();

 System.out.println(" Voiture recu ");
 System.out.println(" ca vient de : " + packet.getAddress() + ":" +
packet.getPort());

//lire carburant d'user
System.out.println("donner la quantité de carburant a remplire : ");
int c= keyb.nextInt();

//set_carburant
v_recu.setCarburant(c);

// Affichage de la nouvelle valeur de carburant par capacité
System.out.println("Nouvelle valeur de carburant : " + v_recu.getCarburant() + "/" + v_recu.getCapacite());


 // on met une nouvelle donnée dans le paquet
 // (qui contient donc le couple @IP/port de la socket coté client)
 byte[] reponse = (new String("bien recu")).getBytes();
 packet.setData(reponse);
 packet.setLength(reponse.length);
 // on envoie le paquet au client
 socket.send(packet);
 } catch (Exception e) {
 System.err.println("Erreur : " + e);
 }
 }
}