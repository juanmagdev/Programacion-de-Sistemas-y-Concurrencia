package semaforos;

import java.util.concurrent.Semaphore;

public class Cafeteria {

	private int MAX_AFORO;
	private int n =0; // numero personas actuales
	private Semaphore mutex = new Semaphore(1, true);
	private Semaphore puedeEntrar = new Semaphore(1, true);
	private Semaphore estaPagado = new Semaphore(0, true);
	private Semaphore espera = new Semaphore(0, true);
	private Semaphore preparo = new Semaphore(0, true);	

	public Cafeteria(int max) {
		MAX_AFORO = max;
	}

	public void entrarCafeteria(int id) throws InterruptedException {
		puedeEntrar.acquire();
		mutex.acquire();
		n++;
		System.out.println("El cliente " + id + " ha entrado en la cafeteria.					Hay " + n + "personas");
		if((n+1)<=MAX_AFORO){
			puedeEntrar.release();	//podemos dar permiso
		} 
		if (n==1){	//cuando hay un cliente doy permiso. Para que sea binario. Vuelvo a dar permiso en _________
			preparo.release();
		}
		mutex.release();
	}

	public void esperarCafe(int id) throws InterruptedException {
		espera.acquire();
		System.out.println("	El cliente " + id + " ha cogido su cafe.");
	}

	public void prepararCafe() throws InterruptedException {
		preparo.acquire();
		System.out.println("							El barista prepara un cafe");
		espera.release();
		estaPagado.acquire();
		
	}

	public void pagarySalir(int id) throws InterruptedException {
		mutex.acquire();
		n--;
		puedeEntrar.release();
		if (n>0) preparo.release();
		System.out
				.println("El cafe ha sido pagado por " + id+ 	"		El cliente " + id + " ha salido. 									Hay " + n + " personas");
		estaPagado.release();
		mutex.release();

	}

	public static void main(String[] args) {
		int MAX_AFORO = 10;
		int NUM_CLIENTES = 25;

		Cafeteria c = new Cafeteria(MAX_AFORO);
		Barista b = new Barista(c);
		Cliente[] clientes = new Cliente[NUM_CLIENTES];

		for (int i = 0; i < NUM_CLIENTES; i++) {
			clientes[i] = new Cliente(i, c);
		}
		b.start();

		for (int i = 0; i < NUM_CLIENTES; i++) {
			clientes[i].start();
		}
	}

}
