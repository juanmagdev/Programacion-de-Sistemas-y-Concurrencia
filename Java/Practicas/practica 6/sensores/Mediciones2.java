//version 2
package sensores;

import java.util.concurrent.Semaphore;

public class Mediciones2 {
	Semaphore semaforos[];
	Semaphore mutex = new Semaphore(1, true);
	Semaphore trabajador = new Semaphore(0, true);
	int n = 0;	//numero de mediciones hechas

	public Mediciones2() {
		this.semaforos = new Semaphore[3];
		for (int i = 0; i < semaforos.length; i++) {
			semaforos[i] = new Semaphore(1, true);
		}
	}

	/**
	 * El sensor id deja su mediciÃ³n y espera hasta que el trabajador
	 * ha terminado sus tareas
	 * 
	 * @param id
	 * @throws InterruptedException
	 */
	public void nuevaMedicion(int id) throws InterruptedException {

		semaforos[id].release();
		mutex.acquire();
		n++;
		if (n == 3)
			trabajador.release();
		mutex.release();

		semaforos[id].acquire();

		System.out.println("Sensor " + id + " deja sus mediciones.");

	}

	/*
	 * El trabajador espera hasta que estÃ¡n las tres mediciones
	 * 
	 * @throws InterruptedException
	 */
	public void leerMediciones() throws InterruptedException {

		trabajador.acquire();

		System.out.println("El trabajador tiene sus mediciones...y empieza sus tareas");

	}

	/**
	 * El trabajador indica que ha terminado sus tareas
	 * 
	 * @throws InterruptedException
	 */
	public void finTareas() throws InterruptedException {
		System.out.println("El trabajador ha terminado sus tareas");

		mutex.acquire();
		n = 0;
		mutex.release();
		for (int i = 0; i < 3; i++) {
			semaforos[i].release();
		}

	}

}