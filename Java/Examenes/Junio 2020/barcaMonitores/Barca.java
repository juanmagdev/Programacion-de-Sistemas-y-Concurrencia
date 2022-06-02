package barcaMonitores;

public class Barca {
	
	private int pasajeros = 0; //numero de pasajeros actuales en la barca
	private boolean puedeSubir = true;
	private boolean puedeBajar = false;
	private boolean finViaje = false;
	private int orilla =0; //orilla sur -> 0 orilla norte -> 1 cambio orilla -> orilla = (orilla +1)%2
	/*
	 * El Pasajero id quiere darse una vuelta en la barca desde la orilla pos
	 */
	public synchronized void subir(int id,int pos) throws InterruptedException{
		while(!puedeSubir || pasajeros ==3 || orilla != pos) wait();
		pasajeros++;
		System.out.println("El pasajero " + id + " ha subido en la orilla " + pos + ". 		" + pasajeros + " actuales");
		notifyAll();
	}
	
	/*
	 * Cuando el viaje ha terminado, el Pasajero que esta en la barca se baja
	 */
	public synchronized int bajar(int id) throws InterruptedException{
		while(puedeBajar == false) wait();
		pasajeros--;
		System.out.println("	El pasajero " + id + " ha bajado en la otra orilla. 		" + pasajeros + " actuales");
		if (pasajeros ==0){
			System.out.println("	Todos los pasajeros se han bajado");
			puedeSubir =true;
		}

		int cop = orilla;
		notifyAll();
		return cop;

	}
	/*
	 * El Capitan espera hasta que se suben 3 pasajeros para comenzar el viaje
	 */
	public synchronized void esperoSuban() throws InterruptedException{
		while(pasajeros != 3 || puedeSubir==false) wait();
		puedeSubir = false; 
		System.out.println("\n Todos los pasajeros han subido a la barca");
		System.out.println("El viaje ha comenzado");
		finViaje = true;
		notifyAll();
	}
	/*
	 * El Capitan indica a los pasajeros que el viaje ha terminado y tienen que bajarse
	 */
	public synchronized void finViaje() throws InterruptedException{
		while(finViaje == false) wait();
		puedeBajar = true;
		orilla = (orilla +1)%2;
		System.out.println("			El viaje ha terminado. Los pasajeros estan en la orilla " + orilla);
		finViaje = false;
		notifyAll();
	}

}
