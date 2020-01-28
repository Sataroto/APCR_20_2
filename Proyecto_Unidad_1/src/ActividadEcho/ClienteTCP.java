/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ActividadEcho;
import PrimerClienteServidorTCP.*;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author Alumno
 */
public class ClienteTCP {
    private Socket cliente;
    private InputStream entrada;
    private OutputStream salida;
    private DataInputStream mensajeEntrada;
    private DataOutputStream mensajeSalida;
    private int puerto_server;
    private String ip_servidor;
    
    public ClienteTCP(int puerto, String ip){
        try {
            this.puerto_server=puerto;
            this.ip_servidor=ip;
            cliente =new Socket(ip_servidor,puerto_server);
            entrada = cliente.getInputStream();
            salida = cliente.getOutputStream();
            
                enviar();
                Thread.sleep(4000);
                recibir();
            cliente.close();
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean enviar(){
        boolean cerrar = true;
        String mensaje;
        mensaje = "prueba";
        if(mensaje.equals("")){
            cerrar=true;
        }
        else{
         mensajeSalida = new DataOutputStream(salida);
        try {
            mensajeSalida.writeUTF(mensaje);
            cerrar=false;
        } catch (IOException ex) {
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
        }   
        }
        return cerrar;
        
    }
    public void recibir(){
        try {
            mensajeEntrada = new DataInputStream(entrada);
            if(this.mensajeEntrada.readUTF().equals("")){
            }
            else{
                    System.out.println("Host: "+this.mensajeEntrada.readUTF());
        }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static void main(String naharuto[]){
        ClienteTCP prueba = new ClienteTCP(1234,"127.0.0.1");
    }
}
