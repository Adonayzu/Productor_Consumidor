package productorConsumidor;

import java.util.Queue;

class Productor extends Thread {
    private Queue<Integer> cola;
    private int capacidad;

    // Constructor para el productor
    public Productor(Queue<Integer> cola, int capacidad) {
        this.cola = cola;
        this.capacidad = capacidad;
    }

    @Override
    public void run() {

    }
}



