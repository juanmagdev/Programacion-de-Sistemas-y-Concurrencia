package barcaSemaforos;

import java.util.concurrent.Semaphore;

public class Barca {
	int pos_barca = 1;
	boolean viajando = false;
	int gente = 0;
	boolean lleno = false;
	int para_bajar = 0;
	int preparados_en_la_otra_orilla = 0;

	Semaphore mutex = new Semaphore(1, true);
	Semaphore bajar = new Semaphore(0, true);
	Semaphore viajar = new Semaphore(0, true);

	Semaphore en_orilla_1 = new Semaphore(0, true);
	Semaphore en_orilla_0 = new Semaphore(0, true);	

	public void subir(int id, int pos) throws InterruptedException {
		mutex.acquire();
		System.out.println("Pide sitio " + id + " en el lado " + pos);
		while (((pos == 0) && (pos_barca == 1)) || ((pos == 0) && (pos_barca == 0) && (gente == 3))) {
			mutex.release();
			en_orilla_0.acquire();
			mutex.acquire();
		}
		while ((pos == 1) && (pos_barca == 0) && (gente == 3) || ((pos == 1) && (pos_barca == 1) && (gente == 3))) {
			mutex.release();
			en_orilla_1.acquire();
			mutex.acquire();
		}

		gente++;
		System.out.println("Se ha subido el numero " + id + " en la orilla " + pos_barca);
		if (gente != 3) {
			mutex.release();
		} else {
			para_bajar = 3;
			mutex.release();
			viajar.release();
		}

	}

	/*
	 * Cuando el viaje ha terminado, el Pasajero que esta en la barca se baja
	 */
	public int bajar(int id) throws InterruptedException {
		bajar.acquire();
		mutex.acquire();
		para_bajar--;
		System.out.println("Se ha bajado el numero " + id + " en la orilla " + pos_barca);
		if (para_bajar == 0) {
			gente = 0;
			for (int i = 0; i < 3; i++) {
				if (pos_barca == 0) {
					en_orilla_0.release();
				} else {
					en_orilla_1.release();
				}
			}


		}else{
			bajar.release();
		}


		mutex.release();
		return pos_barca;

	}

	/*
	 * El Capitan espera hasta que se suben 3 pasajeros para comenzar el viaje
	 */
	public void esperoSuban() throws InterruptedException {
		viajar.acquire();
		System.out.println("Nos vamos de viaje desde la orilla " + pos_barca);

	}

	/*
	 * El Capitan indica a los pasajeros que el viaje ha terminado y tienen que
	 * bajarse
	 */
	public void finViaje() throws InterruptedException {
		mutex.acquire();
		pos_barca = (pos_barca + 1) % 2;
		System.out.println("Hemos llegado a la orilla " + pos_barca);
		bajar.release();
		mutex.release();
		
	}
}