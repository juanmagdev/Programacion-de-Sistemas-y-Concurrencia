package agua;

import java.util.concurrent.*;

public class GestorAgua {

	private Semaphore SOxigeno = new Semaphore(1, true); // protege a numOxigenoDisp
	private Semaphore SHidrogeno = new Semaphore(1, true); // protece a numHidrogenoDisp
	private Semaphore mutex = new Semaphore(1, true); // seccion critica
	private int numOxigenoDisp = 0;
	private int numHidrogenoDisp = 0;

	public void hListo(int id) throws InterruptedException {
		SHidrogeno.acquire(); // si no tiene permisos se duerme
		mutex.acquire();
		if (numHidrogenoDisp == 1) { // habia un atomo de hidrogeno
			numHidrogenoDisp++;
			System.out
					.println("Hidrogeno " + id + " esta listo. [ " + numHidrogenoDisp + " , " + numOxigenoDisp + " ]");
			if (numOxigenoDisp == 1) {
				System.out.println("		Se ha formado una molecula de agua. Ultimo Hidrogeno");
				numOxigenoDisp = 0;
				numHidrogenoDisp = 0;
				SHidrogeno.release();
				SOxigeno.release();
			}

		} else if (numHidrogenoDisp == 0) { // no habia ningun atomo de Hidrogeno
			numHidrogenoDisp++;
			System.out
					.println("Hidrogeno " + id + " esta listo. [ " + numHidrogenoDisp + " , " + numOxigenoDisp + " ]");
			SHidrogeno.release();
		}
		mutex.release();

	}

	public void oListo(int id) throws InterruptedException {
		if (SOxigeno.availablePermits() > 1)
			System.err.println("Error, pillo permisos de mas xdd");

		SOxigeno.acquire(); // si no tiene permisos se duerme
		mutex.acquire(); // Seccion critica
		numOxigenoDisp++;
		System.out.println("Oxigeno " + id + " esta listo. [ " + numHidrogenoDisp + " , " + numOxigenoDisp + " ]");
		if (numHidrogenoDisp == 2) { // podemos formar la molecula
			System.out.println("		Se ha formado una molecula de agua. Ultimo Oxigeno");
			numHidrogenoDisp = 0;
			numOxigenoDisp = 0;
			SOxigeno.release();
			SHidrogeno.release();
		} else if (numOxigenoDisp == 1) {
			SHidrogeno.release();
		}
		
		mutex.release(); // salgo seccion critica
	}
}