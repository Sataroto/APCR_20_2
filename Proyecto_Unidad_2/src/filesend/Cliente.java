/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesend;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;

/**
 *
 * @author sndmonreal
 */
public class Cliente {

    static int serverPort;
    static String filename;

    public static void main(String args[]) {
        try {
            int count = 0;
            int MAX_SIZE = 1048;

            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IpAddress = InetAddress.getByName("localhost");

            byte[] sendData = new byte[MAX_SIZE];

            String filePath = "archivo.txt";
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);

            int totLength = 0;

            while ((count = fis.read(sendData)) != -1)
            {
                totLength += count;
            }

            System.out.println("Longitud total :" + totLength);

            int noOfPackets = totLength / MAX_SIZE;
            System.out.println("No de paquetes : " + noOfPackets);

            int off = noOfPackets * MAX_SIZE;

            int lastPackLen = totLength - off;
            System.out.println("\nLongitud del último paquete : " + lastPackLen);

            byte[] lastPack = new byte[lastPackLen - 1];

            fis.close();
            
            byte[] enviar = new byte[32];
            String mensaje = noOfPackets+","+lastPackLen+",";
            enviar = mensaje.getBytes();
            DatagramPacket sendPacket1 = new DatagramPacket(enviar, enviar.length, IpAddress, 1234);
            clientSocket.send(sendPacket1);
            FileInputStream fis1 = new FileInputStream(file);
            
            while ((count = fis1.read(sendData)) != -1) {
                if (noOfPackets <= 0) {
                    break;
                }
                System.out.println(new String(sendData));
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IpAddress, 1234);
                clientSocket.send(sendPacket);
                System.out.println("último paquete enviado" + sendPacket);
                noOfPackets--;
            }

            System.out.println("\núltimo paquete\n");
            System.out.println(new String(sendData));

            lastPack = Arrays.copyOf(sendData, lastPackLen);

            System.out.println("\nActual último paquete\n");
            System.out.println(new String(lastPack));
             sendPacket1 = new DatagramPacket(lastPack, lastPack.length, IpAddress, 1234);
            clientSocket.send(sendPacket1);
            System.out.println("último paquete enviado" + sendPacket1);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}