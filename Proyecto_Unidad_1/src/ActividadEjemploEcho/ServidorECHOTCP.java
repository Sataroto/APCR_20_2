/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ActividadEjemploEcho;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.util.Scanner;
/**
 *
 * @author Alumno
 */
public class ServidorECHOTCP {
    private ServerSocket servidor;
    private Socket cliente;
    private InputStream entrada;//Fluejo
    private OutputStream salida;//Flujo
    private String ip, protocolo;
    private int puerto;
    private DataInputStream mensajeEntrada;//mensaje
    private DataOutputStream mensajeSalida;//mensaje
    
    public ServidorECHOTCP(){
        try {
            puerto = 1234;
            servidor = new ServerSocket(puerto);
            System.out.println("Servidor a la escucha...");
            while(true){
            cliente = servidor.accept();
            InetAddress ipCliente = cliente.getInetAddress();
            System.out.println("Se conecto: " + ipCliente.getHostAddress());

          /*  // enviar mensaje al cliente
            if(enviar()){
            cliente.close();
            recibir();    
            }
            recibir();
            }*/
            entrada = cliente.getInputStream();
            salida = cliente.getOutputStream();
            //
            mensajeEntrada = new DataInputStream(entrada);
            String aux = mensajeEntrada.readUTF();
            System.out.println("Cliente "+ aux);
            //
            Scanner sc = new Scanner(System.in);
            String mensaje="x";
            while(!(mensaje = sc.nextLine()).equals("Adios"))
            {
                mensajeSalida = new DataOutputStream(salida);
                if(mensaje.equals("")){
                    break;
                }
                mensajeSalida.writeUTF(mensaje);
                mensajeEntrada = new DataInputStream(entrada);
                aux = mensajeEntrada.readUTF();
                if (aux == ""){
                    break;
                }
                System.out.println("cliente:" + aux); 
            }
                mensajeSalida = new DataOutputStream(salida);
                mensajeSalida.writeUTF(mensaje);

            cliente.close();
        }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public boolean enviar(){
        boolean cerrar=true;    
        try {
            
            salida = cliente.getOutputStream();
            String mensaje;
            mensaje = JOptionPane.showInputDialog("Enviar Mensaje");
            mensajeSalida = new DataOutputStream(salida);
            if(mensaje.equals("")){
                
            }
            else {
                try {
                    mensajeSalida.writeUTF(mensaje);
                    cerrar=true;
                } catch (IOException ex) {
                    Logger.getLogger(ServidorECHOTCP.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return cerrar;
        } catch (IOException ex) {
            Logger.getLogger(ServidorECHOTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cerrar;
        
    }
    public void recibir() throws IOException{
            entrada = cliente.getInputStream();
            mensajeEntrada = new DataInputStream(entrada);
            System.out.println("Respuesta: "+this.mensajeEntrada.readUTF());
    }
    public static void main(String []asdas){
        ServidorECHOTCP prueba= new ServidorECHOTCP();
    }
}
