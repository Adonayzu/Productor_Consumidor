package productorConsumidor;

import java.util.Queue;

class Consumidor extends Thread {
    private Queue<Integer> cola; //Queue<Integer> --> almacena los numeros enteros
    private int[] conteoDecenas; // variable de tipo array para almacenar multiples datos
    private int capacidad; //agrege esta variable para que el consumidor notifique cuando este vacia la cola

    // Constructor para el consumidor
    // Queue<Integer> cola--> representa la cola compartida donde se almacenan los numeros generados por el productor
    //  int[] conteoDecenas --> se utiliza para contar los numeros en los direfentes rangos
    public Consumidor(Queue<Integer> cola, int[] conteoDecenas, int capacidad) {
        this.cola = cola; // se refiere al atributo cola de la instancia acutal de la clase consumidor --- = --> es el parametro del constructor
        this.conteoDecenas = conteoDecenas;
        this.capacidad = capacidad;
    }

    @Override
    public void run() {
        try{
            while(true){
                synchronized (cola){ // solo un hilo se ejecute a la vez
                    while(cola.isEmpty()){ // el isEmpty ---> que la cola está vacía entonces tiene que esperar
                        cola.wait();
                    }
                    int numero = cola.poll(); // aqui con el poll() --> se consume los elementos producidos por el productor ya que estaria lleno
                    if (numero == -1) break; // que si la variable es = -1 enonces se detendra el ciclo while true
                    int decena = (numero - 1) / 10; // Este calcula a que decena pertenece el numero se le resta 1 para cuando la decena es igual al numero y luego se divide
                    conteoDecenas[decena]++; // incrementa el contador de la decena que corresponda y se le suma 1 ya que empieza de 0
                    System.out.println("El consumidor proceso: " + numero + " en el rango " + (decena * 10 + 1) + "-" + ((decena + 1) * 10)); // imprime que el consumidor ha procesado un numero

                   // cola.notifyAll();
                    if (cola.size() < capacidad){  // que sea menor a capacidad para que notifique que hay espacio e la cola
                        cola.notifyAll(); // notifica al productor que hay espacio disponible
                    }


                }

            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}

