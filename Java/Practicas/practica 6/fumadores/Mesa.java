package fumadores;

import java.util.concurrent.Semaphore;

public class Mesa {

	//implementacion pasiva de fumadores

	private Semaphore[] fumadoresEsperan = new Semaphore[3];
	private Semaphore agenteEspera = new Semaphore(1, true);

	public Mesa() {
		for (int i = 0; i < fumadoresEsperan.length; i++) {
			fumadoresEsperan[i] = new Semaphore(0, true);
		}
	}

	public void qFumar(int id) throws InterruptedException {
		fumadoresEsperan[id].acquire();
		System.out.println("Fumador " + id + " coge los ingredientes");

	}

	public void finFumar(int id) {
		System.out.println("Fumador " + id + " ha terminado de fumar");
		agenteEspera.release();

	}

	public void nuevosIng(int ing) throws InterruptedException { // se pasa el ingrediente que no se pone

		agenteEspera.acquire(); // Si esto no esta aqui el codigo se ejecuta completo dos veces antes de
								// bloquearse
		System.out.print("El agente ha puesto los ingredientes ");
		if (ing == 0)
			System.out.println("1 y 2");
		else if (ing == 1)
			System.out.println("0 y 2");
		else
			System.out.println("0 y 1");
		fumadoresEsperan[ing].release();

	}

}

// CS-Fumador i: No puede fumar hasta que el fumador anterior no ha terminado
// de fumar y sus ingredientes están sobre la mesa

// CS-Agente: no puede poner nuevos ingredientes hasta que el fumador anterior
// no ha terminado de fumar