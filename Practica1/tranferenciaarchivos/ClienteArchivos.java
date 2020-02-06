package transferenciaarchivos;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClienteArchivos{
    private Socket cliente;
    private InputStream entrada;
    private OutputStream salida;
    private int puerto_server;
    private String ip_servidor;

    public ClienteArchivos(int puerto, String ip){
        try{
            this.puerto_server=puerto;
            this.ip_servidor = ip;
            cliente = new Socket(ip_servidor, puerto_server);
            entrada = cliente.getInputStream();
            salida = cliente.getOutputStream();
            //
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("\\recibido.pdf"));
            
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}