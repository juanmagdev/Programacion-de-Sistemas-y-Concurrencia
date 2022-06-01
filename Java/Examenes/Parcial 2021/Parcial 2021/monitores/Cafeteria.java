package monitores;

public class Cafeteria {

	private int MAX_AFORO;
	private int n; // numero personas actuales
	private int cafes = 0;
	private boolean pagado  =false;

	public Cafeteria(int max) {
		MAX_AFORO = max;
	}

	public synchronized void entrarCafeteria(int id) throws InterruptedException {
		while (n > (MAX_AFORO-1))	
			wait();
		n++;
		System.out.println("El cliente " + id + " ha entrado en la cafeteria.					Hay " + n + "personas");
		notify();
	}

	public synchronized void prepararCafe() throws InterruptedException {
		pagado = false;
		while (n == 0)
			wait();
		System.out.println("							El barista prepara un cafe");
		cafes++;
		while(pagado == false) wait();
		notifyAll();

	}

	public synchronized void esperarCafe(int id) throws InterruptedException {
		while (cafes == 0)
			wait();
		cafes--;
		System.out.println("	El cliente " + id + " ha cogido su cafe.");
		notifyAll();
	}

	public synchronized void pagarySalir(int id) throws InterruptedException {
		pagado = true;
		n--;

		//deberia esperar para salir
		System.out
				.println("El cafe ha sido pagado por " + id+ 	"		El cliente " + id + " ha salido. 									Hay " + n + " personas");
		notifyAll();
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
