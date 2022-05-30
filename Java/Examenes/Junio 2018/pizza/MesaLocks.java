package pizza;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MesaLocks {

    private int nPorcionesDisponibles = 8; // suponemos que una pizza se divide en 8 porciones
    private Lock l = new ReentrantLock(true);
    private Condition esperaPizza = l.newCondition();
    private Condition pidePizza = l.newCondition();


    /**
     * 
     * @param id
     *           El estudiante id quiere una ración de pizza.
     *           Si hay una ración la coge y sigue estudiando.
     *           Si no hay y es el primero que se da cuenta de que la mesa está
     *           vacía
     *           llama al pizzero y
     *           espera hasta que traiga una nueva pizza. Cuando el pizzero trae la
     *           pizza
     *           espera hasta que el estudiante que le ha llamado le pague.
     *           Si no hay pizza y no es el primer que se da cuenta de que la mesa
     *           está vacía
     *           espera hasta que haya un trozo para él.
     * @throws InterruptedException
     * 
     */
    public void nuevaRacion(int id) throws InterruptedException {
        l.lock();
        if (nPorcionesDisponibles == 0)
            pidePizza.signal();
            esperaPizza.await();

        nPorcionesDisponibles--;
        System.out.println("El estudiante " + id + " ha cogido una porcion. Hay " + nPorcionesDisponibles + " porciones disponibles");
        l.unlock();
    }

    /**
     * El pizzero entrega la pizza y espera hasta que le paguen para irse
     * 
     * @throws InterruptedException
     */
    public void nuevoCliente() throws InterruptedException {
        l.lock();
        while(nPorcionesDisponibles == 0) pidePizza.await();
        System.out.println("El pizzero ha recibido un nuevo pedido.");
        l.unlock();
    }

    /**
     * El pizzero espera hasta que algún cliente le llama para hacer una pizza y
     * llevársela a domicilio
     * 
     * @throws InterruptedException
     */
    public void nuevaPizza() throws InterruptedException {
        l.lock();
        nPorcionesDisponibles =8;
        System.out.println("Hay una nueva pizza. Hay un total de " + nPorcionesDisponibles + " porciones disponibles");
        l.unlock();

}
