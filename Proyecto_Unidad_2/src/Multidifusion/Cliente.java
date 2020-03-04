package Multidifusion;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Cliente{
    private MulticastSocket cliente;
    private InetAddress ip_grupo;
    private byte[] buffer;
    private DatagramPacket paquete;
    private int puerto;

    public Cliente(){
        try {
            puerto = 1234;
            cliente = new MulticastSocket(puerto);
            ip_grupo = InetAddress.getByName("224.10.10.25");
            cliente.joinGroup(ip_grupo);
            buffer = new byte[512];

            paquete = new DatagramPacket(buffer, buffer.length);
            cliente.receive(paquete);
            System.out.println(new String(paquete.getData(),0,paquete.getLength()));
            cliente.leaveGroup(ip_grupo);
            cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Cliente hola = new Cliente();    
    }
}