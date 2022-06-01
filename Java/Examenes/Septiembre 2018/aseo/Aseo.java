package aseo;

import java.util.concurrent.Semaphore;

public class Aseo {

	private Semaphore hayHombre = new Semaphore(1, true);
	private Semaphore hayMujer = new Semaphore(1, true);
	private Semaphore mutex = new Semaphore(1, true);
	private int numPersonas = 0;
	private int numMujeres = 0;
	private int numHombres = 0;

	
	/**
	 * El hombre id quiere entrar en el aseo. 
	 * Espera si no es posible, es decir, si hay alguna mujer en ese
	 * momento en el aseo
	 */
	public void llegaHombre(int id) throws InterruptedException{
		mutex.acquire();
		hayMujer.acquire();	//quitamos permiso a las mujeres
		numPersonas++;
		System.out.println("El hombre "+  id + " esta dentro. 	Hay " +  numPersonas + " personas");
		mutex.release();
	}
	/**
	 * La mujer id quiere entrar en el aseo. 
	 * Espera si no es posible, es decir, si hay algun hombre en ese
	 * momento en el aseo
	 */
	public void llegaMujer(int id) throws InterruptedException{
		mutex.acquire();
		hayHombre.acquire();	//quitamos permiso a las hombres
		numPersonas++;
		System.out.println("La mujer "+  id + " esta dentro. 	Hay " +  numPersonas + " personas");
		mutex.release();
	}
	/**
	 * El hombre id, que estaba en el aseo, sale
	 */
	public void saleHombre(int id)throws InterruptedException{
		mutex.acquire();
		numPersonas--;
		System.out.println("	El hombre "+  id + " ha salid. 	Hay " +  numPersonas + " personas");

		if (numPersonas ==0){	// nno hay nadie, damos permiso a las mujeres
			hayMujer.release();
		}
		mutex.release();
	}
	
	public void saleMujer(int id)throws InterruptedException{
		mutex.acquire();
		numPersonas--;
		System.out.println("	La mujer "+  id + " ha salid. 	Hay " +  numPersonas + " personas");
		if (numPersonas ==0){	// nno hay nadie, damos permiso a las mujeres
			hayHombre.release();
		}
		mutex.release();
	}
}
