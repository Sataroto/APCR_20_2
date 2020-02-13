package NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;

import javax.swing.JFileChooser;

public class cliente  {
    public cliente() throws IOException{
        JFileChooser j = new JFileChooser();//Un objeto que nos va permitir escojer un archivo
        j.showSaveDialog(null);//La forma de la seleccion del archivo
        SocketChannel cliente = SocketChannel.open();//La posibilidad de poder conectarse como cliente un servidor
        InetSocketAddress socketAddr = new InetSocketAddress("localhost",9000);//La direccion a la que quiere conectarse
        cliente.connect(socketAddr);//la conexion 
        cliente.configureBlocking(false);//Declarandola como no bloqueante
        Path path = (j.getSelectedFile().toPath());//utiliza la direccion del arivho
        FileChannel channel = FileChannel.open(path);//Abre el archivo
        ByteBuffer buffer = ByteBuffer.allocate(1024);//Hace un medio de transporte
        while(channel.read(buffer)>0){//Le la linea del archivo
            buffer.flip();//Se prepara para escribir
            cliente.write(buffer);//Escribe en el buffer
            buffer.clear();//Lo que ya se envio, se limpia para poder leer de nuevo
        }
        channel.close();//finalizo el envio del archivo
        System.out.println("Archivo enviado");//Confirma en la consola
        cliente.close();//Cierra la conexion
    }
    
public static void main(String[] args) throws IOException {
    cliente cl = new cliente();
}
    
}