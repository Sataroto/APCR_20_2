/*
*Nombre: Gerardo Ayala Juárez
*tema del programa: Programación e implementación de un servicio de venta de boletos multihilo. 
*descripcion: El test tiene un arreglo de cliente y un semaforo, donde el objeto cajera se llama cine y tiene que iniciar mediante venta.
*fecha:17/03/2020
*/
package ejercicio1;
import java.util.concurrent.Semaphore;
public class test{
    public static void main(String[] args) {
        Semaphore sem = new Semaphore(5,true);
        Cliente[] lista = new Cliente[10];
        lista[0] = new Cliente("Gerardo", 3,sem,1);
        lista[1] = new Cliente("Erick", 1,sem,2);
        lista[2] = new Cliente("Ricardo", 2,sem,3);
        lista[3] = new Cliente("Anette", 1,sem,4);
        lista[4] = new Cliente("Celia", 3,sem,5);
        lista[5] = new Cliente("Evangelina", 2,sem,5);
        lista[6] = new Cliente("Gilberto", 1,sem,1);
        lista[7] = new Cliente("Ivan", 1,sem,4);
        lista[8] = new Cliente("Cesar", 2,sem,1);
        lista[9] = new Cliente("Karla", 3,sem,2);
        /*lista[10] = new Cliente("Nidia", 1,sem,2);
        lista[11] = new Cliente("Paola", 2,sem,4);
        lista[12] = new Cliente("Marisol", 1,sem,3);
        lista[13] = new Cliente("Patricio", 4,sem,2);
        lista[14] = new Cliente("Ramiro", 2,sem,1);*/

        Cajera cine = new Cajera(lista);
        cine.venta();
    }
}