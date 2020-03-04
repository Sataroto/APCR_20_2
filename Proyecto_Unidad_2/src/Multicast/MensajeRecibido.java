package Multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

public class MensajeRecibido implements Runnable {
    private MulticastSocket socket;
    private DatagramPacket paq;
    private byte buffer[];
    private String nombre;
    
    public MensajeRecibido(MulticastSocket s,String nombre){
        socket = s;
        this.nombre=nombre;
    }
    public void run() {
        while(true){
            try{
                buffer = new byte[1024];//Cuando desconocemos el valor de la longitud lo seteamos en 1024
                paq = new DatagramPacket(buffer, buffer.length);
                socket.receive(paq);
                int nomsg=0;
              /*  for(int i = 0; i<buffer.length;i++){
                    if(buffer[i]==0){
                        nomsg=i;
                        break;
                    }
                }*/
                byte msg[]=new byte[paq.getLength()];
                System.arraycopy(paq.getData(),0,msg,0,paq.getLength());
                String imprimir = new String(msg,"UTF8");
                String verificacion[] = imprimir.split(":");
                if(!(verificacion[0].equals(nombre))){
                    System.out.println(new String(msg,"UTF8"));
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}