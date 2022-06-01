package barcaSemaforos;

import java.util.concurrent.Semaphore;

public class Barca {
	
	private int pasajeros = 0; //numero de pasajeros actuales en la barca
	private int orilla =0; //orilla sur -> 0 orilla norte -> 1 cambio orilla -> orilla = (orilla +1)%2

	private Semaphore sPasajeros = new Semaphore(1, true); //al principio pueden entrar pasajeros
	private Semaphore MUTEX = new Semaphore(1, true);
	private Semaphore subenTodos = new Semaphore(0, true); //al principio no han subido todos
	private Semaphore finViaje = new Semaphore(0, true);   //al principio el viaje no ha terminado	
	private Semaphore puedeBajar = new Semaphore(0, true);	
	//private Semaphore puedeSubir = new Semaphore(1, true);
	//private Semaphore sOrilla = new Semaphore(1, true);

	/*
	 * El Pasajero id quiere darse una vuelta en la barca desde la orilla pos
	 */
	public  void subir(int id,int pos) throws InterruptedException{

		sPasajeros.acquire(); //si no tiene permisos se duerme
		MUTEX.acquire();	  //seccion critica
		pasajeros++;
		System.out.println("El pasajero " + id + " ha subido en la orilla " + pos + ". 		" + pasajeros + " actuales");

		if(pasajeros < 3){	//si ya hay 3 pasajeros no damos permiso
			sPasajeros.release(); 
		}
		if(pasajeros ==3){
			subenTodos.release();
		}


		MUTEX.release();
	}
	
	/*
	 * Cuando el viaje ha terminado, el Pasajero que esta en la barca se baja
	 */
	public  int bajar(int id) throws InterruptedException{
		puedeBajar.acquire();
		MUTEX.acquire();
		pasajeros--;
		System.out.println("	El pasajero " + id + " ha bajado en la otra orilla. 		" + pasajeros + " actuales");
		if (pasajeros ==0){
			System.out.println("\n	Todos los pasajeros se han bajado \n");
			sPasajeros.release();
		}
		MUTEX.release();
		puedeBajar.release();
		
		return orilla;
	}
	/*
	 * El Capitan espera hasta que se suben 3 pasajeros para comenzar el viaje
	 */
	public  void esperoSuban() throws InterruptedException{
		subenTodos.acquire(); //si no tiene permiso se duerme
		MUTEX.acquire();
		System.out.println("\n Todos los pasajeros han subido a la barca");
		System.out.println("El viaje ha comenzado");
		finViaje.release();
		MUTEX.release();
	}
	/*
	 * El Capitan indica a los pasajeros que el viaje ha terminado y tienen que bajarse
	 */
	public  void finViaje() throws InterruptedException{
		finViaje.acquire();
		MUTEX.acquire();
		orilla = (orilla +1)%2;
		System.out.println("\n			El viaje ha terminado. Los pasajeros estan en la orilla " + orilla);
		puedeBajar.release();
		MUTEX.release();
	}

}
