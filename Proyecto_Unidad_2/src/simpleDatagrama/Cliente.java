package simpleDatagrama;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Cliente{
    private DatagramSocket cliente;
    private DatagramPacket recibe,envia;
    private byte[] enviar, recibir;
    private int port=1234;
    private InetAddress ip_serv;
    public Cliente(String mensaje){
        try {
            ip_serv = InetAddress.getByName("localhost");
            cliente = new DatagramSocket();
            enviar = mensaje.getBytes();
            envia = new DatagramPacket(enviar, enviar.length,ip_serv,port);
            cliente.send(envia);
            recibir = new byte[100];
            recibe = new  DatagramPacket(recibir,recibir.length);
            cliente.receive(recibe);
            System.out.println("Datagrama recibido: "+new String(recibe.getData()));
            System.out.println("IP "+recibe.getAddress().getHostAddress() + " Puerto: "+recibe.getPort());
            cliente.close();

        } catch (SocketException se) {
            se.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Cliente C = new Cliente("Perros calientes 2x1");
    }
}