package sensores;

import java.util.Random;

public class Trabajador implements Runnable {
    private Random r = new Random();

    private Mediciones m;

    public Trabajador(Mediciones m) {
        this.m = m;
    }

    public void run() {
        while (true) {
            try {
                m.leerMediciones();
                Thread.sleep(r.nextInt(100));
                m.finTareas();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
