package Seriable;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor{
    private ServerSocket servidor;
    private Socket cliente;
    private InputStream entrada;
    private OutputStream Salida;
    private ObjectInputStream obj_entrada;
    private ObjectOutputStream obj_Salida;
    private int puerto = 1234;
    public Servidor(){
        try{
            servidor = new ServerSocket(puerto);
            cliente = servidor.accept();

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}