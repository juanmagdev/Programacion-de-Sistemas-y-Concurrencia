package impresoras;

import java.util.*;

public class Cliente implements Runnable {

    private static Random r = new Random();
    private SalaImpresoras sala;
    private int id;

    public Cliente(SalaImpresoras sala, int id) {
        this.sala = sala;
        this.id = id;
    }

    public void run() {

        try {
            Thread.sleep(r.nextInt(2000));
            int n = sala.quieroImpresora(id);
            Thread.sleep(r.nextInt(600));
            sala.devuelvoImpresora(id, n);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
