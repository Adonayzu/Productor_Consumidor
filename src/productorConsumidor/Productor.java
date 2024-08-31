package productorConsumidor;

import java.util.Queue;
import java.util.Random;

class Productor extends Thread {
    private Queue<Integer> cola; //Queue<Integer> --> almacena los numeros enteros
    private int capacidad; //capacidad maxima

    // Constructor para el productor
    public Productor(Queue<Integer> cola, int capacidad) {
        this.cola = cola;
        this.capacidad = capacidad;
    }

    @Override
    public void run() {
        Random aleatorio = new Random();
        try{
            for (int i =0; i < 1000; i++){
                synchronized (cola) { // ayuda para que solo un hilo se ejecute a la vez
                    while (cola.size() == capacidad){
                        cola.wait();
                    }
                    int valor = aleatorio.nextInt(100)+ 1; //genera un numero aleatorio de 0 a 99 y se le suma 1 para que sea el numero correcto del 1 al 100
                    cola.add(valor); // se agrega el valor a la cola
                    System.out.println("El productor a producido: " + valor);


                    if(cola.size() == capacidad){ // si es igual a la cola que es 10 que notifique al consumidorq que esta lleno
                        cola.notifyAll();
                    }

                }
            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }


    }
}



