package babuinos.babuinosSemaforos;

import java.util.concurrent.Semaphore;

//Solucion usando monitores
public class Cuerda {
	private int nBabuinosActuales = 0; // inicialmente hay 0
	private boolean direcciónNS = false;
	private Semaphore mutex = new Semaphore(1, true);
	private Semaphore nB = new Semaphore(1, true);
	private Semaphore dir =  new Semaphore(1, true);

	/**
	 * Utilizado por un babuino cuando quiere cruzar el cañón colgándose de la
	 * cuerda en dirección Norte-Sur
	 * Cuando el método termina, el babuino está en la cuerda y deben satisfacerse
	 * las dos condiciones de sincronización
	 * 
	 * @param id del babuino que entra en la cuerda
	 * @throws InterruptedException
	 */
	public synchronized void entraDireccionNS(int id) throws InterruptedException {
		

	}

	/**
	 * Utilizado por un babuino cuando quiere cruzar el cañón colgándose de la
	 * cuerda en dirección Norte-Sur
	 * Cuando el método termina, el babuino está en la cuerda y deben satisfacerse
	 * las dos condiciones de sincronización
	 * 
	 * @param id del babuino que entra en la cuerda
	 * @throws InterruptedException
	 */
	public synchronized void entraDireccionSN(int id) throws InterruptedException {

	}

	/**
	 * Utilizado por un babuino que termina de cruzar por la cuerda en dirección
	 * Norte-Sur
	 * 
	 * @param id del babuino que sale de la cuerda
	 * @throws InterruptedException
	 */
	public synchronized void saleDireccionNS(int id) throws InterruptedException {

	}

	/**
	 * Utilizado por un babuino que termina de cruzar por la cuerda en dirección
	 * Sur-Norte
	 * 
	 * @param id del babuino que sale de la cuerda
	 * @throws InterruptedException
	 */
	public synchronized void saleDireccionSN(int id) throws InterruptedException {

	}

}
