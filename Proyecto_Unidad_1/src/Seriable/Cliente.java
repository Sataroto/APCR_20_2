package Seriable;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Cliente{
    private ServerSocket serv;
    private Socket cliente;
    private InputStream entrada;
    private OutputStream salida;
    private ObjectInputStream obj_entrada;
    private ObjectOutputStream obj_salida;
    
    public Cliente(String host, int port){
        try {
            cliente = new Socket(host,port);
            entrada = cliente.getInputStream();
            salida = cliente.getOutputStream();
            Empleado e1 = new Empleado("nombre", "direccion", "tel", "tel", "nss", "curp");
            obj_salida = new ObjectOutputStream(salida);
            obj_salida.writeObject(e1);

            cliente.close();

        } catch (IOException e) {
            //TODO: handle exception
        }
    }
}