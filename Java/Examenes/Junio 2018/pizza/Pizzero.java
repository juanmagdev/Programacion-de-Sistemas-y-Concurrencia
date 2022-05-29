package pizza;

import java.util.Random;

public class Pizzero extends Thread {
	private Mesa mesa;

	private static Random r = new Random();

	public Pizzero(Mesa mesa) {
		this.mesa = mesa;
	}

	public void run() {
		while (true) {
			try {
				mesa.nuevoCliente();
				Thread.sleep(r.nextInt(500));
				mesa.nuevaPizza();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
