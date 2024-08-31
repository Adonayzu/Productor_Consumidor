package productorConsumidor;

import java.util.LinkedList;
import java.util.Queue;

public class ProductorConsumidor {
    public static void main(String[] args) {
        Queue<Integer>  cola = new LinkedList<>(); // LinkedList--> Se puede añadir o eliminar elementos sin preocuparse por el tamaño inicial
        int capacidad = 10; // capacidad de la cola
        int [] conteoDecenas = new int [10]; // sirve para contar numeros de diferentes rangos en decenas

        Productor productor = new Productor(cola, capacidad);
        Consumidor consumidor = new Consumidor(cola, conteoDecenas,capacidad);

        productor.start();

        consumidor.start();

        try{
            productor.join(); //Espera a que el productor termine su ejecucion
            synchronized (cola){    // solo un hilo se pueda ejecutar en este bloque d codigo
                cola.add(-1); // sirve como ua señal para el consumidor deba detenerse y el -1 es par que cuando consuma y llegue a ese valor se detenga
                cola.notifyAll();
            }
            consumidor.join(); // Espera a que el hilo cosumidor termine de ejecutarse
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        //Para mostrar el resultado final
        for (int i = 0; i < 10; i++){
            int rangoInferior = i *10 +1;
            int rangoSuperior = (i + 1) *10;
            System.out.println("La Cantidad de numeros entre " + rangoInferior + " y " + rangoSuperior + ": " + conteoDecenas[i]);
        }
    }
}
