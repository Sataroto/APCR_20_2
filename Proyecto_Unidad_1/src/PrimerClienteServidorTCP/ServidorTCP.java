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
/**
 *
 * @author Alumno
 */
public class ServidorTCP {
    private ServerSocket servidor;
    private Socket cliente;
    private InputStream entrada;
    private OutputStream salida;
    private String ip, protocolo;
    private int puerto;
    private DataInputStream mensajeEntrada;
    private DataOutputStream mensajeSalida;
    
    public ServidorTCP(){
        try {
            puerto = 1234;
            servidor = new ServerSocket(puerto);
            System.out.println("Servidor a la escucha...");
            cliente = servidor.accept();
            InetAddress ipCliente = cliente.getInetAddress();
            System.out.println("Se conecto: " + ipCliente.getHostAddress());
            entrada = cliente.getInputStream();
            salida = cliente.getOutputStream();
            // enviar mensaje al cliente
            mensajeSalida = new DataOutputStream(salida);
            mensajeSalida.writeChars("Voy por la banqueta Perro D:<");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    } 
}
