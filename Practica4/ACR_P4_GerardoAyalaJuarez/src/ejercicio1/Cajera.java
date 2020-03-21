/*
*Nombre: Gerardo Ayala Juárez
*tema del programa: Programación e implementación de un servicio de venta de boletos multihilo. 
*descripcion: La clase cajero tiene un funcionamiento con un numero determinado de posibles cajeras que trabajan con una fila de clientes.
*fecha:17/03/2020
*/
package ejercicio1;

import java.util.Random;

public class Cajera {
    public static double ticket=50.00;
    private static String[] peliculas = {"Roma", "Parasyte", "La princesa Mononoke", "Mi vecino Totoro", "El gran Gatsby", "Lazos a traves del tiempo", "La tesis Final"};
    public static String[] nombres ={"Patricia","Norma","Dulce","Lucina","Brenda"};
    private static int lim = 5; 
    private Cliente[] fila ;
    private int[] conteo={0,0,0,0,0};
    public Cajera(Cliente[] lista){
        fila=lista;
    }
    public void venta(){
        for (int i =0 ; i< fila.length;i++){
            for(int j =0 ; j<lim;j++){
                if(i<fila.length){
                    System.out.println("La cajera "+ nombres[j]+" comienza a atender al cliente "+fila[i].getNombre()+ "::>");
                    fila[i].start();
                    try {
                        fila[i].join();
                    } catch (Exception e) {
                        //TODO: handle exception
                    }
                    System.out.println("La cajera "+ nombres[j]+ " termino con el cliente: "+fila[i].getNombre()+ " en " + (getRandomNumberInRange(1, 180)) + " seg\n" );
                    conteo[j]++;
                    if(j==4){
                        i--;
                    }
                    i++;
                }
            }
        }
        for(int i =0; i<lim;i++){
            System.out.println("La cajera "+nombres[i]+" atendio :>"+conteo[i]+" clientes");
        }
    }
    public static String pelicula(int i){
        return peliculas[i];
    }
    private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
}