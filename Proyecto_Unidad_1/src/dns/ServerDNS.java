/*
Nombre: Gerardo Ayala Juarez
Tema del programa:DNS simple con Sockets de flujo
Descripcion: Servidor DNS que responde con dominio o ip segun la solicitud que se haga
Fecha:30 de enero del 2020

 */
package dns;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.util.Scanner;
import dns.direcciones;
public class ServerDNS{
    private ServerSocket servidor;
    private Socket cliente;
    private InputStream entrada;//Fluejo
    private OutputStream salida;//Flujo
    private String ip, protocolo;
    private int puerto;
    private DataInputStream mensajeEntrada;//mensaje
    private DataOutputStream mensajeSalida;//mensaje
    private direcciones[] libreta;
    public ServerDNS(){
            try {
                String solicitud;
                String respuesta="-1";
                String aux[];
                libreta = new direcciones[5];
                libreta[0] = new direcciones("google.com");
                aux = new String[]{"248.79.193.131","88.179.196.40","160.232.173.3","2.232.104.252","154.78.185.172"} ;
                libreta[0].setRepertorio(aux);
                libreta[1] = new direcciones("facebook.com");
                aux = new String[]{"202.32.228.35","223.105.188.115","24.63.30.53","123.130.203.73","182.207.89.225"} ;
                libreta[1].setRepertorio(aux);
                libreta[2] = new direcciones("twitter.com");
                aux = new String[]{"96.6.75.121","24.165.119.23","204.137.194.9","167.15.28.54","13.229.112.250"} ;
                libreta[2].setRepertorio(aux);
                libreta[3] = new direcciones("reddit.com");
                aux = new String[]{"130.51.53.251","128.137.219.222","148.254.93.76","87.203.6.42","61.126.55.196"} ;
                libreta[3].setRepertorio(aux);
                libreta[4] = new direcciones("Youtube.com");
                aux = new String[]{"202.32.228.35","223.105.188.115","24.63.30.53","123.130.203.73","182.207.89.225"} ;
                libreta[4].setRepertorio(aux);
                
                puerto = 1234;
                servidor = new ServerSocket(puerto);
                System.out.println("Servidor a la escucha...");
                while(true){
                cliente = servidor.accept();
                InetAddress ipCliente = cliente.getInetAddress();
                System.out.println("Se conecto: " + ipCliente.getHostAddress());
                //
                entrada = cliente.getInputStream();
                salida = cliente.getOutputStream();
                //
                mensajeSalida = new DataOutputStream(salida);
                mensajeSalida.writeUTF("Bienvenido");
                while(true){
                    mensajeEntrada = new DataInputStream(entrada);
                    solicitud = mensajeEntrada.readUTF();
                    if(!(solicitud.equals("Adios"))){
                    
                    for(int i=0;i<libreta.length;i++){
                        respuesta = libreta[i].getDominio(solicitud);
                        if (!(respuesta.equals("-1"))){
                            break;
                        }
                    }
                    if(respuesta.equals("-1")){
                    for(int i=0;i<libreta.length;i++){
                        if(libreta[i].getNombre().equals(solicitud)){
                            respuesta = libreta[i].getOpciones();
                        }  
                    }
                    }
                    mensajeSalida = new DataOutputStream(salida);
                    mensajeSalida.writeUTF(respuesta);
                }
                if(solicitud.equals("Adios"))
                cliente.close();
            }
            
                }
            } catch (Exception e) {
                //TODO: handle exception
            }
            
           
    }
    public void recibir() throws IOException{
        entrada = cliente.getInputStream();
        mensajeEntrada = new DataInputStream(entrada);
        System.out.println("Respuesta: "+this.mensajeEntrada.readUTF());
    }
    public static void main(String[] aggg){
        ServerDNS prueba1 = new ServerDNS();
    }
}
