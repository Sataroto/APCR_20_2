/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HiloTCP;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.JTextArea;

/**
 *
 * @author sndmonreal
 */
public class Conexion extends Thread{
    private JTextArea area;
    private Socket cliente=null;
    private BufferedReader entrada;
    private DataOutputStream salida;
    private String llego,IP;
    public Conexion(Socket cliente, JTextArea area){    
        this.area=area;
        this.cliente=cliente;
        IP=cliente.getInetAddress().toString();
    }
    public void run(){
        try{
            entrada=new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            salida = new DataOutputStream(cliente.getOutputStream());
            do{
                llego=entrada.readLine();
                area.append(llego);
                salida.writeInt(llego.length());
            }while(llego.length()!=0);

            entrada.close();
            cliente.close();
        }catch(Exception e){e.printStackTrace();}
        area.append("Se desconecto"+IP);
    }
    
}
