package parejas;

import java.util.*;

public class Mujer implements Runnable {
	private int id;
	private Sala sala;
	private static Random r = new Random();

	public Mujer(int id, Sala sala) {
		this.id = id;
		this.sala = sala;
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				sala.llegaMujer(id);
				Thread.sleep(r.nextInt(500));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
