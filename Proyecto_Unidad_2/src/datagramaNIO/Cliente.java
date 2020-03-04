package datagramaNIO;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Cliente{
    private DatagramChannel canal;
    private ByteBuffer buff;
    private int port;
    private String ip;

    public Cliente(int puerto, String ip){
        this.port = puerto;
        this.ip = ip;
        try {
            canal = DatagramChannel.open();
            //No bloqueante
            canal.configureBlocking(false);

            canal.bind(null);
            System.out.println("Cliente activo");
            enviarMensaje("uwu");
        } catch (IOException e) {
            e.printStackTrace();//TODO: handle exception
        }
    }
    private void enviarMensaje(String mensaje) {
        buff = ByteBuffer.wrap(mensaje.getBytes());
        //
        InetSocketAddress target = new InetSocketAddress(ip,port);
        // enviar
        try {
            canal.send(buff, target);
            buff.clear();
            System.out.println("Mensaje enviado...");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void recibirMensaje() {
        buff = ByteBuffer.allocate(1024);
        while(true){
            try {
                if( canal.receive(buff)!=null){
                    buff.flip();
                    String retroalimentacion = new String(buff.array());
                    System.out.println("El cliente dice: "+ retroalimentacion);
                    buff.clear();
                }                               
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    public static void main(String[] args) {
        Cliente f = new Cliente(1234,"localhost");
    }
}