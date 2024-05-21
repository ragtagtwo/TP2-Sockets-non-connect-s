import java.net.*;
import java.io.*;
import java.util.Scanner;
import f_voiture.Voiture;
import java.io.Serializable;


public class client {
 public static void main(String argv[]) {
 int port = 0;
 String host = "";
 Scanner keyb = new Scanner(System.in);
 try {
 // on récupère les paramètres : nom de la machine serveur et 
 // numéro de port 
 System.out.println("Adress du serveur : ");
 host = keyb.next();
 System.out.println("port d'écoute du serveur : ");
 port = keyb.nextInt();
 InetAddress adr;
 // adr contient l'@IP de la partie serveur
 adr = InetAddress.getByName(host);

//creer l'objet
Voiture myCar = new Voiture("SUV", "Toyota RAV4");

// Serialize the Voiture object
ByteArrayOutputStream bos = new ByteArrayOutputStream();
ObjectOutputStream oos = new ObjectOutputStream(bos);
oos.writeObject(myCar);
oos.flush();
byte[] data = bos.toByteArray();
// Send the serialized object over UDP
DatagramSocket socket = new DatagramSocket();
DatagramPacket packet = new DatagramPacket(data, data.length, adr, port);
 // envoi du paquet via la socket
 socket.send(packet);
 
 // création d'un tableau vide pour la réception
 byte[] reponse = new byte[15];
 packet.setData(reponse);
 packet.setLength(reponse.length);
 // attente paquet envoyé sur la socket du client
 socket.receive(packet);
 // récupération et affichage de la donnée contenue dans le paquet
 String chaine = new String(packet.getData(), 0,
packet.getLength());
 System.out.println(" reçu du serveur : " + chaine);
 } catch (Exception e) {
 System.err.println("Erreur : " + e);
 }
 }
}