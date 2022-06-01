package impresoras;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class SalaImpresorasS implements SalaImpresoras {

    private int TAM;    //numero impresoras
    private int impresorasDadas;
    private Semaphore mutex = new Semaphore(1, true);
    private Semaphore hayImpresoras = new Semaphore(1, true);
    private List<Integer> impresoras = new LinkedList<>();  //lista de impresoras disponibles

    public SalaImpresorasS (int tam) {
        TAM = tam;
        impresorasDadas = tam;  //inicalmente tenemos todas las impresoras
        for(int i = 0; i < tam; i ++) {
            impresoras.add(i);
        }
    }

    @Override
    public int quieroImpresora(int id) throws InterruptedException {

        System.out.println("Cliente " + id + " quiere impresora ");

        hayImpresoras.acquire();

        mutex.acquire();
        
        impresorasDadas --;

        System.out.println("    Cliente " + id + " coge impresora " + impresoras.get(0) + " quedan libres " + impresorasDadas);

        if(impresorasDadas > 0) {   //si podemos seguir dando impresoras, damos permiso
            hayImpresoras.release();
        }

        int impresora = impresoras.remove(0); //quito el primer elemento

        mutex.release();

        return impresora;
    }

    @Override
    public void devuelvoImpresora(int id, int n) throws InterruptedException {
        mutex.acquire();

        impresorasDadas ++;

        System.out.println("        Cliente " + id + " devuelve la impresora " + n + " quedan libres " + impresorasDadas);

        impresoras.add(n);

        if(impresorasDadas == 1) {
            hayImpresoras.release();
        }

        mutex.release();
    }
}
