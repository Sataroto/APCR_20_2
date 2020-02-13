package transferenciaarvhivos;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;

public class ServidorArchivos{
private ServerSocket servidor;
private Socket cliente;
private InputStream entrada;
private OutputStream salida;
private int puerto=1234;

private String file_to_send;//nombre del Archivo

public ServidorArchivos(String archivo){
    file_to_send=archivo;

    //Crear el servidor
    try{
        servidor = new ServerSocket(puerto);
        System.out.println("Servidor a la escucha");
        while(true){//Este es el ciclo que acepta clientes, se hace con un ciclo infinito par que trabaje con mas de un cliente.
            cliente = servidor.accept();
            System.out.println("Se conecto: "+ cliente);
            File elarchivo = new File(file_to_send);
            if(elarchivo.exists()){
                entrada = new OutputStream();
                String name = elarchivo.getName();
                DataInputStream nombre = new DataInputStream(entrada);
                nombre.writeUTF(name);
                //Archivo como flujo de datos;
                byte arreglo[] = new byte[(int)elarchivo.length()];
                //Lectura Local del archivo
                FileInputStream fis = new FileInputStream(elarcivo);
                BufferedInputStream bis = new BufferedInputStream(fis);
                bis.read(arreglo,0,arreglo.length);//Lectura del arhivo local;
                salida = new Outputstream();
                System.out.println("Enviando..."+file_to_send+" ("+arreglo.length+" bytes)");
                //Enviar el Archivo
                salida.write(arreglo, 0, arreglo.length);
                cliente.close();
            }
        }
    }
    catch(IOException e){
        e.printStackTrace();
    }
}

}