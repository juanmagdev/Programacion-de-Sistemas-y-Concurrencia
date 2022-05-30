package recursos;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Control {
	private int NUM; // numero total de recursos
	private int numRec;
	private List<Integer> queue = new LinkedList<>();
	private Lock lck = new ReentrantLock();
	private Condition esperarRec = lck.newCondition();
	private Condition esperarTurno = lck.newCondition();

	public Control(int num) {
		this.NUM = num;
		this.numRec = num;
	}

	public synchronized void qRecursos(int id, int num) throws InterruptedException {

		System.out.println("Proceso " + id + " pide " + num + " recursos. Quedan: " + numRec);

		queue.add(id);
		while ((numRec < num) || queue.indexOf(id) != 0) {
			wait();
		}

		queue.remove(0);
		numRec -= num;
		notifyAll();

		System.out.println("El proceso " + id + " ha cogido " + num + " recursos.	Quedan: " + numRec);

	}

	public synchronized void libRecursos(int id, int num) {
		numRec += num;
		notifyAll();
		System.out.println("El proceso " + id + " ha liberado " + num + " recursos.	Recursos totales: " + numRec);
	}

	// public void qRecursos(int id, int num) throws InterruptedException {
	// lck.lock();
	// try {
	// System.out.println("Proceso " + id + " pide " + num + " recursos. Quedan: " +
	// numRec);

	// queue.add(id);

	// while (queue.indexOf(id) != 0)
	// esperarTurno.await();

	// while (numRec < num)
	// esperarRec.await();

	// queue.remove(0);
	// numRec -= num;
	// esperarTurno.signal();

	// System.out.println("El proceso " + id + " ha cogido " + num + " recursos.
	// Quedan: " + numRec);
	// } finally {
	// lck.unlock();
	// }

	// }

	// public void libRecursos(int id, int num) {
	// lck.lock();
	// try {
	// numRec += num;
	// System.out.println("El proceso " + id + " ha liberado " + num + " recursos.
	// Recursos totales: " + numRec);
	// esperarRec.signal();
	// } finally {
	// lck.unlock();
	// }

	// }

}
// CS-1: un proceso tiene que esperar su turno para coger los recursos
// CS-2: cuando es su turno el proceso debe esperar hasta haya recursos
// suficiente
// para él