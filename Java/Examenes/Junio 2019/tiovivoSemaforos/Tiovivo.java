package tiovivoSemaforos;
import java.util.concurrent.Semaphore;

public class Tiovivo {
	private int plazas;
	private int pasajeros = 0;
	private Semaphore subir = new Semaphore(1, true); //de primeras puede subir la gente
	private Semaphore bajar = new Semaphore(0, true); //al principio no puede bajar la gente, no esta subida
	private Semaphore vuelta = new Semaphore(0, true);
	private Semaphore mutex = new Semaphore(1, true);

	public Tiovivo (int n){
		this.plazas = n;
	}
	
// 	método usado por un pasajero que quiere subir a
// la atracción. Un pasajero no puede subirse hasta que el tiovivo está parado, no haya
// pasajeros del paseo anterior y haya espacio
	public void subir(int id) throws InterruptedException{	
		subir.acquire();
		mutex.acquire(); //regio critica
		pasajeros++;
		System.out.println("Pasajero " + id + " se sube. Pasajeros: " + pasajeros);
		if (pasajeros < plazas) {
			subir.release(); //deja turno para que se suban
		} else{ //pasajeros == 5
			vuelta.release(); //deja turno para que arranque
		}
		mutex.release();
	}
	
// 	método usado por un pasajero que quiere bajar
// de la atracción. Un pasajero no puede bajar de la atracción hasta que no dé un paseo y
// el tiovivo haya parado.
	public void bajar(int id) throws InterruptedException{
		bajar.acquire();
		mutex.acquire();
		pasajeros--;
		System.out.println("Pasajero " + id + " se baja. Pasajeros: " + pasajeros);
		if (pasajeros == 0) {
			System.out.println("Se han bajado todos los pasajeros.");
			subir.release(); //deja libre turno para que se suban
			vuelta.release(); //deja libre turno para que arranque

		} else{ //pasajeros == 0
			bajar.release(); //deja turno para que se bajen
		}
		mutex.release();
	}
	
// 	método usado por el operario para que los
// pasajeros que estaban en la cola puedan subirse hasta completar todas las plazas.
// Cuando no quedan plazas libres el tiovivo se pone en marcha
	public void esperaLleno () throws InterruptedException{
		vuelta.acquire(); //coge turno para iniciar la vuelta
		System.out.println("Esperando");
	}
	
// 	método usado por el operario parar el tiovivo y dar
// paso a que los pasajeros que estaban dentro puedan bajarse.

	public void finViaje () throws InterruptedException{
		bajar.release(); //deja turno para que se bajen
		System.out.println("El viaje ha terminado");
	}

}
