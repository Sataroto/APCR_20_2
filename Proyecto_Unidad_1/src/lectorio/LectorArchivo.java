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
            archivo = "datos.txt";d
            fis = new FileInputStream(archivo);
            canal = fis.getChannel();
            buffer = ByteBuffer.allocateDirect(1024);

        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}