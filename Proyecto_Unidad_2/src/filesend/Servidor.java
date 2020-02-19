/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesend;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author sndmonreal
 */
public class Servidor {
    
    public static void main(String args[]) {
        try{
        DatagramSocket serverSocket = new DatagramSocket(1234);//Creamos el socket orientado a datagramas
        byte[] recData = new byte[1024];//la forma del paquete
        byte[] recibir = new byte[32];
        int i =0;//El numero de paquete que se han enviado;

        FileWriter file = new FileWriter("copia.txt");//El archvio donde se va escribir
        PrintWriter out = new PrintWriter(file);//La forma en que se va escribir en el nuevo archivo 

        while(true)
        {
 
            DatagramPacket recibe = new  DatagramPacket(recibir,recibir.length);
            serverSocket.receive(recibe);
            //Recibe el numero de paquetes
            String datos = new String(recibe.getData());
            String[] mensaje = datos.split(",");
            int numPackets = Integer.parseInt(mensaje[0]);
            int lonoflast = Integer.parseInt(mensaje[1]);
            //Recibe la longitud del ultimo numero

            
            for(i=0;i<numPackets-1;i++){
                DatagramPacket recPacket = new DatagramPacket(recData, recData.length);
                serverSocket.receive(recPacket);
                String line = new String(recPacket.getData());
                System.out.println("\n Datos: " + line);
                out.println(line);
                System.out.println("\nPaquete" + (++i) + " escrito en el archivo\n");
                out.flush();
            }

            DatagramPacket last = new DatagramPacket(recData,lonoflast);
            serverSocket.receive(last);
                String line = new String(last.getData());
                System.out.println("\n Datos: " + line);
                out.println(line);
                System.out.println("Ultimo paquete" + (++i) + " escrito en el archivo\n");
                out.flush();

            
        }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
