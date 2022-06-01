package mrusa;

import java.util.concurrent.locks.*;

public class Coche extends Thread {
	// Empezamos creando el lock y metiendolo donde tiene que ir para que no se
	// olvide

	private Lock l = new ReentrantLock();
	private Condition cEsperaLleno = l.newCondition();
	private Condition cEsperaCoche = l.newCondition();
	private Condition cEsperaViaje = l.newCondition();

	private int C, numPasajeros=0;

	public Coche(int tam) {
		C = tam;
	}

	public void subir(int id) throws InterruptedException {
		// id del pasajero que se sube al coche
		l.lock();
		try {
			while (numPasajeros == C)
				cEsperaLleno.await();
			numPasajeros++;
			System.out.println("El pasajero " + id + " se monta en el coche");
			if (numPasajeros == C) {
				// El ultimo en subirse lo llena
				cEsperaCoche.signal();
				cEsperaViaje.await();

			} else {
				// Quedan huecos asique hay que esperar

				cEsperaViaje.await();
			}

		} finally {
			l.unlock();
		}

	}

	public void bajar(int id) throws InterruptedException {
		// id del pasajero que se baja del coche
		l.lock();
		try {
			numPasajeros--;
			System.out.println("El pasajero " + id + " se baja del coche");
			if (numPasajeros == 0)
				cEsperaLleno.signalAll();
		} finally {
			l.unlock();
		}
	}

	private void esperaLleno() throws InterruptedException {
		// el coche espera a que este lleno para dar una vuelta
		l.lock();
		try {
			cEsperaCoche.await();
			System.out.println("Nos damos una vuelta.");
			cEsperaViaje.signalAll();
		} finally {
			l.unlock();
		}
	}

	public void run() {
		Boolean fin = false;
		while (!this.isInterrupted() && !fin)
			try {
				this.esperaLleno();

			} catch (InterruptedException ie) {
				fin = true;
			}

	}
}