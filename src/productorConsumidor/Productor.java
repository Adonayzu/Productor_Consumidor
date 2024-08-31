package productorConsumidor;

import java.util.Queue;
import java.util.Random;

class Productor extends Thread {
    private Queue<Integer> cola;
    private int capacidad;
    private int ciclos;  // Número de ciclos de producción

    //constructor
    public Productor(Queue<Integer> cola, int capacidad, int ciclos) {
        this.cola = cola;
        this.capacidad = capacidad;
        this.ciclos = ciclos;
    }

    @Override
    public void run() {
        Random aleatorio = new Random();
        try {
            for (int ciclo = 0; ciclo < ciclos; ciclo++) {
                synchronized (cola) {
                    while (cola.size() < capacidad) {
                        int valor = aleatorio.nextInt(100) + 1;
                        cola.add(valor);
                        System.out.println("El productor a producio el número: " + valor);
                    }
                    cola.notifyAll();
                    cola.wait();
                }
            }

            // para detener el  while del consumidor
            synchronized (cola) {
                cola.add(-1); // se agrega a la cola
                cola.notifyAll();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
