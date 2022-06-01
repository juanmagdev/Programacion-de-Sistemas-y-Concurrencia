package pajaritos;

import java.util.concurrent.Semaphore;

public class Nido {
	private static final int MAX = 10;	//numero de bichos maximo
	private int numB = 0;		//numero de bichos iniciales
	private Semaphore mutex = new Semaphore(1);
	private Semaphore hayEspacio = new Semaphore(1, true);
	private Semaphore hayComida = new Semaphore(0, true);

	public void come(int id) throws InterruptedException {
		// el bebe id coge un bichito del nido
		hayComida.acquire();
		mutex.acquire();
		numB--;
		System.out.println("El bebe " + id + " ha comido un bichito. Quedan " + numB);
		if (numB > 0)
			hayComida.release();
		else
			hayEspacio.release();
		/*
		if(numB>0)hayComida.release();
		if(numB<MAX)hayEspacio.release();
		
		if(numB==MAX-1)hayEspacio.release();
		 */
		
		mutex.release();
	}

	public void nuevoBichito(int id) throws InterruptedException {
		// el papa/mama id deja un nuevo bichito en el nido
		hayEspacio.acquire();
		mutex.acquire();		 
		numB++;
		System.out.println("El papa " + id + " ha añadido un bichito. Hay " + numB);
		if(numB>0)hayComida.release();
		if(numB<MAX)hayEspacio.release();
		
		mutex.release();
	}
}

//CS-Bebe-i: No puede comer del nido si estÃ¡ vacÃ­o

//CS-Papa/Mama: No puede poner un bichito en el nido si estÃ¡ lleno