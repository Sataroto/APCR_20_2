package NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class Server {
  
    public Server(int puerto) throws IOException {
        
        ////Inicializacion del servido Bloque
        int i=0;//Numero de archivos que llevara el contero de los archivos recibidos;
        ServerSocketChannel server = null;//Se declara el servido
        SocketChannel client = null;//Se declara el cliente
        server = ServerSocketChannel.open();//Abre la posiblidad de recibir solicitudes de comunicacion
        server.socket().bind(new InetSocketAddress(puerto));//Se inicializa el servidor
        server.configureBlocking(false);//Configuramos que sea no blockeante
        System.out.println("Servidor a la escucha...");
            while (client == null) {//busca hasta que encuentre un cliente conectado
                client = server.accept();//Se encontro con un cliente
            }   
            i++;//Para que genere un diferente nombre al archivo anterior mencionado
            System.out.println("Se conecto: "+client.getRemoteAddress());//Status de que se conecto el cliente.
            Path path = Paths.get("Recibido/archivo_"+i+".txt");//Declaramos la direccion del archivo que nos van a enviar
            FileChannel fileChannel = FileChannel.open(path, EnumSet.of(StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE));//EStablecemos una secuencia o posibilidades, en caso de situaciones previstas.
            ByteBuffer buffer = ByteBuffer.allocate(1024);//La via por las que vamos a enviar
            while(client.read(buffer)>0){//LE una linea del archivo
                    buffer.flip();//cambia el sentido del buffer
                    fileChannel.write(buffer);//Escribe en el archivo lo que se recibio
                    buffer.clear();//Se limpia el buffer para si en caso de ser leido de nuevo tener todo el espacio disponible
            }
                fileChannel.close();//cierra el archvio recibido
            System.out.println("Archivo recibido");//Se confirma la recepcion del archivo
            client.close();//Se cierra conexion con el cliente
        
    }
    public static void main(String[] args) throws IOException {
        Server uwu = new Server(9000);
    }
}