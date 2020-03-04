package Multidifusion;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Servidor{
    private MulticastSocket servidor;
    private InetAddress ip_grupo;
    private byte[] buffer;
    private DatagramPacket paquete;
    private int puerto;

    public Servidor(){
        try {
            puerto = 1234;
            servidor = new MulticastSocket();
            //direccion ip Multicast
            ip_grupo = InetAddress.getByName("224.10.10.25");

            String saludo = "Hola grupo";
            buffer = saludo.getBytes();

            // unirnos al grupo
            servidor.joinGroup(ip_grupo);

            //enviar mensaje
            paquete = new DatagramPacket(buffer,buffer.length,ip_grupo,puerto);
            servidor.send(paquete);//Envia el paquete

            servidor.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Servidor s = new Servidor();
    }
}