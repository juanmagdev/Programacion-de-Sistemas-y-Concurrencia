package parada;

public class Parada {

	private boolean grupoPrincipal; // grupo principal = 1, grupo secundario = 0
	private boolean aParada = false;

	public Parada() {

	}

	/**
	 * El pasajero id llama a este metodo cuando llega a la parada.
	 * Siempre tiene que esperar el siguiente autobus en uno de los
	 * dos grupos de personas que hay en la parada
	 * El metodo devuelve el grupo en el que esta esperando el pasajero
	 * 
	 */
	public synchronized int esperoBus(int id) throws InterruptedException {
		if (aParada == false) {
			System.out.println(" El pasajero " + id + " ha llegado a la parada.	GRUPO: 1");
			return 1;
		}

		else {
			System.out.println(" El pasajero " + id + " ha llegado a la parada.	GRUPO: 1");
			return 0;
		}

	}

	/**
	 * Una vez que ha llegado el autobús, el pasajero id que estaba
	 * esperando en el grupo i se sube al autobus
	 * @throws InterruptedException
	 *
	 */
	public synchronized void subeAutobus(int id, int i) throws InterruptedException {
		while(i==0) wait();
		System.out.println("El pasajeor " + id + " ha subido al bus");

	}

	/**
	 * El autobus llama a este metodo cuando llega a la parada
	 * Espera a que se suban todos los viajeros que han llegado antes
	 * que el, y se va
	 * 
	 */
	public synchronized void llegoParada() throws InterruptedException {
		aParada = true;
		System.out.println("		EL AUTOBUS LLEGO PARADA			");
		notifyAll();
	}
}
