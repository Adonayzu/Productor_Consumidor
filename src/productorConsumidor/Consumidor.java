package productorConsumidor;

import java.util.Queue;

class Consumidor extends Thread {
    private Queue<Integer> cola;
    private int[] conteoDecenas;
    private int capacidad;

    //constructor
    public Consumidor(Queue<Integer> cola, int[] conteoDecenas, int capacidad) {
        this.cola = cola;
        this.conteoDecenas = conteoDecenas;
        this.capacidad = capacidad;
    }

    @Override
    public void run() {
        try {
            while (true) {
                synchronized (cola) {
                    while (cola.isEmpty()) {  // el isEmpty ---> que la cola está vacía entonces tiene que esperar
                        cola.wait();
                    }
                    while (!cola.isEmpty()) {
                        int numero = cola.poll(); // aqui con el poll() --> se consume los elementos producidos por el productor ya que estaria lleno
                        if (numero == -1) {  // Sale de bucle
                            return;
                        }
                        int decena = (numero - 1) / 10;
                        conteoDecenas[decena]++;
                        System.out.println("El consumidor procesó: " + numero + " en el rango " + (decena * 10 + 1) + "-" + ((decena + 1) * 10));
                    }
                    cola.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
