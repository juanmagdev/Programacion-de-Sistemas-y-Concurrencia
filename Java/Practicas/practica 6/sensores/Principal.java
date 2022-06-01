package sensores;

public class Principal {

    public static void main(String[] args) {
        Mediciones m = new Mediciones();
        Thread t = new Thread(new Trabajador(m));
        Thread[] s = new Thread[3];
        for (int i = 0; i < s.length; i++) {
            s[i] = new Thread(new Sensor(i, m));
        }
        t.start();
        for (int i = 0; i < s.length; i++) {
            s[i].start();
        }
    }

}
