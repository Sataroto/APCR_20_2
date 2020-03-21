package Semaforos;

import java.util.concurrent.Semaphore;

public class Vehiculo extends Thread{
    private Semaphore sem;
    private String marca;

    public Vehiculo(String marca, Semaphore s){
        this.marca = marca;
        this.sem=s;
    }

    public void run(){
        try {
            sem.acquire();
            System.out.println("Semaforo verde para: "+marca);
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            sem.release();
        }
    }
}