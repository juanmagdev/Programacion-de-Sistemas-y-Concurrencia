package sensores;

import java.util.Random;

public class Sensor implements Runnable {

    private Random r = new Random();
    private int id;
    private Mediciones m;

    public Sensor(int id, Mediciones m) {
        this.id = id;
        this.m = m;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(r.nextInt(100));
                m.nuevaMedicion(id);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
