/*Nombre: Gerardo Ayala Juárez
*tema del programa: Programación e implementación de un servicio de venta de boletos multihilo. 
*descripcion: El cliente requiere de un semaforo que coordine su funcionamiento, el cliente tiene un nombre, numeros de boletos, y pelicula que quiere ver.
*fecha:17/03/2020*/
package ejercicio1;
import java.util.concurrent.Semaphore;
public class Cliente extends Thread {
    private String nombre;
    private int boleto;
    private Semaphore sem;
    private int movie;
    public Cliente(String name, int ticket, Semaphore sem, int pel){
        nombre = name;
        boleto = ticket;
        this.sem = sem;
        this.movie =  pel-1;
    }
    public String getNombre(){
        return nombre;
    }
    public void run(){
        try {
            sem.acquire();
            System.out.println("Pelicula:-----"+Cajera.pelicula(movie)+"\n"+"No. Boletas:-----"+boleto+"\n"+"Total -----"+boleto*Cajera.ticket);
            sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            sem.release();
        }
    }
}