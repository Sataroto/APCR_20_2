package lectorio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.FileChannel;

public class LectorArchivo{
    private FileInputStream fis;
    private FileChannel canal;
    private ByteBuffer buffer;
    private String archivo;

    public LectorArchivo(){
        try {
            archivo = "datos.txt";
            fis = new FileInputStream(archivo);
            canal = fis.getChannel();
            buffer = ByteBuffer.allocate(1024);
            int bytesleidos = canal.read(buffer);
            while(bytesleidos!=-1){
                System.out.println("Bytes leidos"+bytesleidos);
                buffer.flip();
                    while(buffer.hasRemaining()){
                        System.out.print((char)buffer.get());
                    }
                    buffer.clear();
                    bytesleidos = canal.read(buffer);
            }
            System.out.println();
            fis.close();

        } catch (Exception e) {
            //TODO: handle exception
        }
    }
    public static void main(String[] args) {
        LectorArchivo hola = new LectorArchivo();

    }
}