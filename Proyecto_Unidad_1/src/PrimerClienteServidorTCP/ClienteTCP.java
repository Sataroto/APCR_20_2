/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrimerClienteServidorTCP;
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
            
            while(true){
                recibir();
                if(enviar()){
                cliente.close();
                }
                
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean enviar(){
        boolean cerrar = true;
        String mensaje;
        mensaje = JOptionPane.showInputDialog("Enviar Mensaje");
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
    public void recibir() throws IOException{
            mensajeEntrada = new DataInputStream(entrada);
            System.out.println("Host: "+this.mensajeEntrada.readUTF());
    }
    public static void main(String naharuto[]){
        ClienteTCP prueba = new ClienteTCP(1234,"127.0.0.1");
    }
}
