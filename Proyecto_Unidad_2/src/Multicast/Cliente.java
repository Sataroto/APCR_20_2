package Multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;
public class Cliente
{   
    private String nombre;
    private MulticastSocket socket;
    private int port=1234;
    private String ip_grupo="224.0.0.1";
    private InetAddress address;
    private DatagramPacket paq;
    private byte[] arreglo;

    public Cliente(){
        try {
            socket = new MulticastSocket(port);
            address = InetAddress.getByName(ip_grupo);
            socket.joinGroup(address);
        
            Scanner sc = new Scanner(System.in);
            System.out.println("Ingresa el nombre");
            this.nombre = sc.nextLine();
             //Recibir Mensajes
             MensajeRecibido mr = new MensajeRecibido(socket,this.nombre);
             Thread f = new Thread(mr);
             f.start();//Manda llamar run pero como multiprocesamiento, de otra manera esto seria el llamado normal de un metodo
             //Enviar Mensajes
            System.out.println("Comienza a escribir tus mensajes");
            while(true){
                String mensaje=this.nombre+":dice>>"+sc.nextLine(); 
                arreglo = mensaje.getBytes();
                paq = new DatagramPacket(arreglo,arreglo.length,address,port);
                socket.send(paq);
            } 
            
            
            
        } catch (IOException e) {
            e.printStackTrace();//TODO: handle exception
        }
        
    }
    public static void main(String[] args) {
        Cliente cc = new Cliente();
    }
}