package babuinos.babuinosMonitores;

//Solucion usando monitores
public class Cuerda {
	private int nBabuinosActuales = 0; // inicialmente hay 0
	private boolean direcciónNS = false;

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
		while (nBabuinosActuales > 2 || direcciónNS == false)
			wait();
		nBabuinosActuales++;
		// va en direccionNS -> direccionNS = true;
		System.out.println("El babuino " + id + " entra en direccion NS");
		//if (nBabuinosActuales ==0) direcciónNS = false;
		notify();
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
		while (nBabuinosActuales > 2 || direcciónNS == true)
			wait();
		nBabuinosActuales++;
		// va en direccionSN -> direccionNS = false
		System.out.println("El babuino " + id + " entra en direccion SN");
		//if (nBabuinosActuales ==0) direcciónNS = true;

		notify();
	}

	/**
	 * Utilizado por un babuino que termina de cruzar por la cuerda en dirección
	 * Norte-Sur
	 * 
	 * @param id del babuino que sale de la cuerda
	 * @throws InterruptedException
	 */
	public synchronized void saleDireccionNS(int id) throws InterruptedException {
		// no hay que controlar para que salgan
		nBabuinosActuales--;
		System.out.println("El babuino " + id + " ha salido. Iba en direccion NS");
		if(nBabuinosActuales==0) {
			direcciónNS = !direcciónNS;
			notifyAll();
		}
	}

	/**
	 * Utilizado por un babuino que termina de cruzar por la cuerda en dirección
	 * Sur-Norte
	 * 
	 * @param id del babuino que sale de la cuerda
	 * @throws InterruptedException
	 */
	public synchronized void saleDireccionSN(int id) throws InterruptedException {
		nBabuinosActuales--;
		System.out.println("El babuino " + id + " ha salido. Iba en direccion SN");
		if(nBabuinosActuales==0) {
			direcciónNS = !direcciónNS;
			notifyAll();
		}
	}

}
