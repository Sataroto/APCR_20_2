package Semaforos;

import java.util.concurrent.Semaphore;

public class Crucero{
    private Semaphore sem;
    private Vehiculo[] ve;
    private int limite;
    public Crucero(int lim){
        limite = lim;
        sem = new Semaphore(limite,true);
    }
    public void llenararreglo(){
        ve = new Vehiculo[10];
        ve[0] = new Vehiculo("Vento",sem);
        ve[1] = new Vehiculo("Aveo",sem);
        ve[2] = new Vehiculo("Fiesta",sem);
        ve[3] = new Vehiculo("Liberty",sem);
        ve[4] = new Vehiculo("Odissey",sem);
        ve[5] = new Vehiculo("Freestar",sem);
        ve[6] = new Vehiculo("Windstar",sem);
        ve[7] = new Vehiculo("Civic",sem);
        ve[8] = new Vehiculo("Accord",sem);
        ve[9] = new Vehiculo("Vocho",sem);
    }
    public Vehiculo[] getVehiculos(){
        return this.ve;
    }
    public static void main(String[] args) {
        Crucero c = new Crucero(3);
        c.llenararreglo();
        Vehiculo v[]=c.getVehiculos();
        for(int i = 0; i<10; i++){
            v[i].start();
        }   
    }
}