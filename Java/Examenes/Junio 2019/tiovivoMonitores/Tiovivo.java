package tiovivoMonitores;

public class Tiovivo {

	private int maxPasajeros;
	private int pActuales = 0;
	private boolean puedeSubir = true; // falso si esta en movimiento, o bajando gente
	private boolean puedeBajar = false;

	public Tiovivo(int numPasajeros) {
		maxPasajeros = numPasajeros;
	}

	public synchronized void subir(int id) throws InterruptedException {
		while (pActuales == maxPasajeros || puedeSubir == false)
			wait();
		puedeBajar = false; //ronda de subida, nadie baja
		pActuales++;
		System.out.println("Pasajero: " + id + " ha subido. Hay " + pActuales + " pasajeros subidos");
		if (pActuales == maxPasajeros)
			puedeSubir = false;
		notifyAll();
	}

	public synchronized void bajar(int id) throws InterruptedException {
		while (puedeBajar == false)
			wait();
		puedeSubir = false; //ronda de bajada, nadie sube
		pActuales--;
		System.out.println("Pasajero: " + id + " ha bajado. Faltan por bajar " + pActuales + " pasajeros");

		// pueden subir los siguientes
		if (pActuales == 0){
			System.out.println("------Todos los pasajeros han bajado------");
			puedeSubir = true;
		}

		notifyAll();
	}

	public synchronized void esperaLleno() throws InterruptedException { // espera a que se llene
		while (pActuales < maxPasajeros || puedeSubir == true)
			wait();
		// System.out.println("El tiovivio se ha llenado");
		notify();
	}

	public synchronized void finViaje() throws InterruptedException {
		while(puedeSubir == true && puedeBajar == false)  wait();
		puedeBajar = true;
		puedeSubir = false;
		System.out.println("------El viaje ha terminado------");
		notifyAll();
	}
}
