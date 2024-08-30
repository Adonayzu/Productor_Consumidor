package productorConsumidor;

import java.util.Queue;


class Consumidor extends Thread {
    private Queue<Integer> cola;
    private int[] conteoDecenas;

    // Constructor para el consumidor
    public Consumidor(Queue<Integer> cola, int[] conteoDecenas) {
        this.cola = cola;
        this.conteoDecenas = conteoDecenas;
    }

    @Override
    public void run() {

    }
}

