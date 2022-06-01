package impresoras;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SalaImpresorasML implements SalaImpresoras {

    private Lock l = new ReentrantLock();
    private Condition esperaImpresora = l.newCondition();
    private List<Integer> impresoras = new LinkedList<>();
    private List<Integer> clientes = new LinkedList<>();
    private int impresorasLibres;

    public SalaImpresorasML(int tam) {
        impresorasLibres = tam;
        for (int i = 0; i < tam; i++) {
            impresoras.add(i);
        }
    }

    @Override
    public int quieroImpresora(int id) throws InterruptedException {

        l.lock();
        try {

            System.out.println("Cliente " + id + " quiere impresora ");

            clientes.add(id);

            while (clientes.get(0) != id || impresorasLibres == 0) {
                esperaImpresora.await();
            }

            clientes.remove(0);

            impresorasLibres--;

            System.out.println("    Cliente " + id + " coge impresora " + impresoras.get(0) + " quedan libres "
                    + impresorasLibres);

            return impresoras.remove(0);
        } finally {
            l.unlock();
        }
    }

    @Override
    public void devuelvoImpresora(int id, int n) throws InterruptedException {

        l.lock();
        try {

            impresorasLibres++;

            System.out.println(
                    "        Cliente " + id + " devuelve la impresora " + n + " quedan libres " + impresorasLibres);

            impresoras.add(n);

            esperaImpresora.signalAll();

        } finally {
            l.unlock();
        }
    }
}







