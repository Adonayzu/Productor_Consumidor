package productorConsumidor;

import java.util.LinkedList;
import java.util.Queue;

public class ProductorConsumidor {
    public static void main(String[] args) {
        Queue<Integer> cola = new LinkedList<>();  // LinkedList--> Se puede añadir o eliminar elementos sin preocuparse por el tamaño inicial
        int capacidad = 10;
        int ciclos = 100;
        int[] conteoDecenas = new int[10];

        Productor productor = new Productor(cola, capacidad, ciclos);
        Consumidor consumidor = new Consumidor(cola, conteoDecenas, capacidad);


        productor.start();
        consumidor.start();

        try{
            productor.join();
            consumidor.join();
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }

        // Mostrar el resultado final
        for (int i = 0; i < 10; i++) {
            int rangoInferior = i * 10 + 1;
            int rangoSuperior = (i + 1) * 10;
            System.out.println("La cantidad de números entre " + rangoInferior + " y " + rangoSuperior + ": " + conteoDecenas[i]);
        }
    }
}
