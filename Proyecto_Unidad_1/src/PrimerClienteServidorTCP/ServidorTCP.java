/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrimerClienteServidorTCP;
import java.io.*;
import java.net.*;
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
    
    public ServidorTCP(){
        
    }
    
}
