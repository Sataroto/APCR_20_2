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
public class ClienteECHOTCP {
    private Socket cliente;
    private InputStream entrada;
    private OutputStream salida;
    private DataInputStream mensajeEntrada;
    private DataOutputStream mensajeSalida;
    private int puerto_server;
    private String ip_servidor;
    
    public ClienteECHOTCP(final int puerto, final String ip) {
        try {
            this.puerto_server = puerto;
            this.ip_servidor = ip;
            cliente = new Socket(ip_servidor, puerto_server);
            entrada = cliente.getInputStream();
            salida = cliente.getOutputStream();
            mensajeSalida = new DataOutputStream(salida);
            mensajeEntrada = new DataInputStream(entrada);
            Scanner sc = new Scanner(System.in);
            String mensaje="",aux="";
            while(!(mensaje = sc.nextLine()).equals("Adios"))
            {
               
                mensajeSalida.writeUTF(mensaje);
                aux = mensajeEntrada.readUTF();
                if (aux.equals("")){
                    break;
                }
                System.out.println("servidor: " + aux); 
            }
            mensajeSalida = new DataOutputStream(salida);
            mensajeSalida.writeUTF(mensaje);
            
            cliente.close();

        } catch (final IOException ex) {
            ex.printStackTrace();
        }
    }

    public boolean enviar() {
        boolean cerrar = true;
        String mensaje;
        mensaje = JOptionPane.showInputDialog("Enviar Mensaje");
        if (mensaje.equals("")) {
            cerrar = true;
        } else {
            mensajeSalida = new DataOutputStream(salida);
            try {
                mensajeSalida.writeUTF(mensaje);
                cerrar = false;
            } catch (final IOException ex) {
                Logger.getLogger(ClienteECHOTCP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cerrar;

    }

    public void recibir() throws IOException {
        mensajeEntrada = new DataInputStream(entrada);
        System.out.println("Host: " + this.mensajeEntrada.readUTF());
    }

    public static void main(final String naharuto[]) {
        final ClienteECHOTCP prueba = new ClienteECHOTCP(1234, "127.0.0.1");
    }
}
