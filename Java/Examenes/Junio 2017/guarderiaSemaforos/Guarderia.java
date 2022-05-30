package guarderiaSemaforos;

import java.util.concurrent.Semaphore;

public class Guarderia {

	private int numBebes = 0;
	private int numAdultos = 0;

	private Semaphore mutex = new Semaphore(1); // seccion critica
	private Semaphore entraBebe = new Semaphore(0); // protege a numBebes, al principio no puede entrar ningun bebe
	private Semaphore saleAdulto = new Semaphore(1);// protege a numAdultos

	/**
	 * Un bebe que quiere entrar en la guarderia llama a este metodo.
	 * Debe esperar hasta que sea seguro entrar, es decir, hasta que
	 * cuado entre haya, al menos, 1 adulto por cada 3 bebes
	 * 
	 */
	public void entraBebe(int id) throws InterruptedException {
		entraBebe.acquire(); // si no tiene permisos se duerme
		mutex.acquire(); // seccion critica
		if ((numBebes + 1) <= 3 * numAdultos) {
			numBebes++;
			System.out.println("Ha entrado un bebe. Hay " + numBebes + " bebes y " + numAdultos + " adultos			[" + numAdultos + " , " + numBebes + " ]");
			entraBebe.release();
		}
		mutex.release(); // libera seccion critica
	}

	/**
	 * Un bebe que quiere irse de la guarderia llama a este metodo *
	 */
	public void saleBebe(int id) throws InterruptedException {
		// los bebes pueden salir siempre que quieran
		mutex.acquire(); // seccion critica
		if (numBebes >= 1) {
			numBebes--;
			System.out.println("Ha salido un bebe. Hay " + numBebes + " bebes y " + numAdultos + " adultos			[" + numAdultos + " , " + numBebes + " ]");
		}
		mutex.release();
	}

	/**
	 * Un adulto que quiere entrar en la guarderia llama a este metodo *
	 */
	public void entraAdulto(int id) throws InterruptedException {
		// un adulto siempre puede entrar
		mutex.acquire(); // seccion critica
		numAdultos++;
		System.out.println("Ha entrado un adulto. Hay " + numBebes + " bebes y " + numAdultos + " adultos			[" + numAdultos + " , " + numBebes + " ]");
		entraBebe.release(); // Siempre que entra adulto, podremos dar permisos a los bebes
		mutex.release();
	}

	/**
	 * Un adulto que quiere irse de la guarderia llama a este metodo.
	 * Debe esperar hasta que sea seguro salir, es decir, hasta que
	 * cuando se vaya haya, al menos, 1 adulto por cada 3 bebes
	 * 
	 */
	public void saleAdulto(int id) throws InterruptedException {
		saleAdulto.acquire(); //pido permiso para salir

		mutex.acquire();  	  //seccion critica
		if (numBebes <= 3 * (numAdultos - 1)) {
			numAdultos--;
			System.out.println("Ha salido un adulto. Hay " + numBebes + " bebes y " + numAdultos + " adultos			[" + numAdultos + " , " + numBebes + " ]");
			saleAdulto.release(); //?
		}
		mutex.release(); //seccion critica
	}

}