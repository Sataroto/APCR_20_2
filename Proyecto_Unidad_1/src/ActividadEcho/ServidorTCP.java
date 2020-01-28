/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ActividadEcho;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author Alumno
 */
public class ServidorTCP {
    private ServerSocket servidor;
    private Socket cliente;
    private InputStream entrada;//Fluejo
    private OutputStream salida;//Flujo
    private String ip, protocolo;
    private int puerto;
    private DataInputStream mensajeEntrada;//mensaje
    private DataOutputStream mensajeSalida;//mensaje
    
    public ServidorTCP(){
        try {
            puerto = 1234;
            servidor = new ServerSocket(puerto);
            System.out.println("Servidor a la escucha...");
            while(true){
            cliente = servidor.accept();
            InetAddress ipCliente = cliente.getInetAddress();
            System.out.println("Se conecto: " + ipCliente.getHostAddress());
                echo();
                cliente.close();
            }
            // enviar mensaje al cliente
            
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void echo(){
        try {
            entrada = cliente.getInputStream();
            mensajeEntrada = new DataInputStream(entrada);
            System.out.println("Echo: "+this.mensajeEntrada.readUTF());
            salida = cliente.getOutputStream();
            mensajeSalida = new DataOutputStream(salida);
            mensajeSalida.writeUTF(mensajeEntrada.readUTF());
            
            
        } catch (IOException ex) {
            Logger.getLogger(ServidorTCP.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(ServidorTCP.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return cerrar;
        } catch (IOException ex) {
            Logger.getLogger(ServidorTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cerrar;
        
    }
    public void recibir() throws IOException{
            entrada = cliente.getInputStream();
            mensajeEntrada = new DataInputStream(entrada);
            System.out.println("Respuesta: "+this.mensajeEntrada.readUTF());
    }
    public static void main(String []asdas){
        ServidorTCP prueba= new ServidorTCP();
    }
}
