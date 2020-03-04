package datagramaNIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Servidor{
    private DatagramChannel canal;
    private ByteBuffer buff;
    private int port=1234 ;
    public Servidor(){
        try {
            canal = DatagramChannel.open();
            //no bloquante
            InetSocketAddress address = new InetSocketAddress("localhost", port);
            canal.bind(address);
            System.out.println("Servidor a la escucha...");
            
                recibirMensaje();
            


        } catch (IOException e) {
            //TODO: handle exception
        }
    }
    public void recibirMensaje() {
        SocketAddress add;
        buff = ByteBuffer.allocate(1024);
        while(true){
            try {
                if( (add = canal.receive(buff))!=null){
                    buff.flip();
                    String retroalimentacion = new String(buff.array());
                    System.out.println("El cliente dice: "+ retroalimentacion);
                    buff.clear();
                    enviarMensaje(retroalimentacion,add );

                }                               
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        
    }
    private void enviarMensaje(String mensaje, SocketAddress add) {
        buff = ByteBuffer.wrap(mensaje.getBytes());
        //
        // enviar
        try {
            canal.send(buff, add);
            buff.clear();
            System.out.println("Mensaje enviado...");
            canal.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        Servidor serv= new Servidor();
    }
}