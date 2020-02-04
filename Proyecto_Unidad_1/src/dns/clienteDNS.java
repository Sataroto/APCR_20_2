/*
Nombre: Gerardo Ayala Juarez
Tema del programa:DNS simple con Sockets de flujo
Descripcion: cliente DNS que hace solicitud con dominio o ip segun la respuesta que se busque
Fecha:30 de enero del 2020

 */
package dns;
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
public class clienteDNS {
    private Socket cliente;
    private InputStream entrada;
    private OutputStream salida;
    private DataInputStream mensajeEntrada;
    private DataOutputStream mensajeSalida;
    private int puerto_server;
    private String ip_servidor;
    
    public clienteDNS(final int puerto, final String ip) {
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
            System.out.println(mensajeEntrada.readUTF());
            while(!(mensaje = sc.nextLine()).equals("Adios"))
            {
                mensajeSalida.writeUTF(mensaje);
                aux = mensajeEntrada.readUTF();
                System.out.println("servidor: " + aux); 
            }
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
                Logger.getLogger(clienteDNS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }//248.79.193.131
        return cerrar;

    }

    public void recibir() throws IOException {
        mensajeEntrada = new DataInputStream(entrada);
        System.out.println("Host: " + this.mensajeEntrada.readUTF());
    }

    public static void main(final String naharuto[]) {
        final clienteDNS prueba = new clienteDNS(1234, "127.0.0.1");
    }
}