// version 1
package sensores;

import java.util.concurrent.Semaphore;

public class Mediciones {

    private int numMediciones=0;
    private Semaphore mutex=new Semaphore(1);
    private Semaphore esperoPorSensores=new Semaphore(0);//
    private Semaphore esperoPorTrabajador=new Semaphore(0);

    public Mediciones() {

    }

    /**
     * El sensor id deja su mediciÃƒÂ³n y espera hasta que el trabajador
     * ha terminado sus tareas
     *
     * @param id
     * @throws InterruptedException
     */
    public void nuevaMedicion(int id) throws InterruptedException {

        mutex.acquire();
        System.out.println("Sensor " + id + " deja sus mediciones.");
        numMediciones++;

        if(numMediciones==3) {
            esperoPorSensores.release();
        }

        mutex.release();
        esperoPorTrabajador.acquire();
        mutex.acquire();
        numMediciones--;
        if(numMediciones!=0)esperoPorTrabajador.release();
        mutex.release();


    }

    /***
     * El trabajador espera hasta que estÃƒÂ¡n las tres mediciones
     *
     * @throws InterruptedException
     */
    public void leerMediciones() throws InterruptedException {
        esperoPorSensores.acquire();
        System.out.println("El trabajador tiene sus mediciones...y empieza sus tareas");

    }

    /**
     * El trabajador indica que ha terminado sus tareas
     */
    public void finTareas() {
        System.out.println("El trabajador ha terminado sus tareas");
        esperoPorTrabajador.release();

    }

}