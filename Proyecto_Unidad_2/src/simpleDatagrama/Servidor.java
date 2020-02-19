package simpleDatagrama;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Servidor{
    private DatagramSocket servidor;
    private DatagramPacket recibe,envia;
    private byte[] enviar, recibir;
    private int port=1234;    

    public Servidor(){
        try{
        servidor = new DatagramSocket(port);
        recibir = new byte[100];
        recibe = new  DatagramPacket(recibir,recibir.length);
        while(true){
            servidor.receive(recibe);
            System.out.println("Datagrama recibido: "+new String(recibe.getData()));
            System.out.println("IP "+recibe.getAddress().getHostAddress() + " Puerto: "+recibe.getPort());
    
                enviar = recibir;
                envia = new DatagramPacket(enviar, enviar.length,recibe.getAddress(),recibe.getPort());
                servidor.send(envia);
                servidor.close();
        }
        
        
        }
        catch(SocketException se){
            se.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Servidor s = new Servidor();
    }
}