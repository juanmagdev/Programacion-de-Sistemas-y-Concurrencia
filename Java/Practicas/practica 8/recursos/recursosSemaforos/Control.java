package recursos.recursosSemaforos;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Control {
	private int NUM;// numero total de recursos
	private int numRec;
	private boolean hayGente = false;
	Semaphore mutex = new Semaphore(1, true);
	Semaphore estarcola = new Semaphore(1, true);
	Semaphore pilla_recursos = new Semaphore(0, true);

	int necesito = -1;

	public Control(int num) {
		this.NUM = num;
		this.numRec = num;
	}

	/*
	 * public synchronized void qRecursos(int id, int num) throws
	 * InterruptedException {
	 * turnos.add(id);
	 * System.out.println("Proceso " + id + " pide " + num + " recursos. Quedan: " +
	 * numRec);
	 * while (id != turnos.get(0)) {
	 * wait();
	 * }
	 * while (num > numRec) {
	 * wait();
	 * }
	 * numRec -= num;
	 * turnos.remove(0);
	 * notifyAll();
	 * System.out.println("El proceso " + id + " ha cogido " + num +
	 * " recursos. Quedan: " + numRec);
	 * }
	 * 
	 * public synchronized void libRecursos(int id, int num) {
	 * numRec += num;
	 * notifyAll();
	 * System.out.println("El proceso " + id + " ha liberado " + num +
	 * " recursos. Recursos totales: " + numRec);
	 * }
	 */

	public void qRecursos(int id, int num) throws InterruptedException {
		estarcola.acquire();
		mutex.acquire();
		System.out.println("Proceso " + id + " pide " + num + " recursos. Quedan: " + numRec);
		if (num > numRec) {
			hayGente = true;
			necesito = num;
			mutex.release();
			pilla_recursos.acquire();
			mutex.acquire();
		}
		numRec -= num;
		System.out.println("El proceso " + id + " ha cogido " + num + " recursos. Quedan: " + numRec);
		mutex.release();
		estarcola.release();
	}

	public void libRecursos(int id, int num) throws InterruptedException {
		mutex.acquire();
		numRec += num;
		if ((numRec >= necesito) && (hayGente)) {
			pilla_recursos.release();
			hayGente = false;
		}
		System.out.println("El proceso " + id + " ha liberado " + num + " recursos. Recursos totales: " + numRec);
		mutex.release();

	}
}
// CS-1: un proceso tiene que esperar su turno para coger los recursos
// CS-2: cuando es su turno el proceso debe esperar hasta haya recursos
// suficiente
// para él