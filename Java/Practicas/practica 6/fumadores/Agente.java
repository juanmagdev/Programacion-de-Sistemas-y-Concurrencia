package fumadores;

import java.util.*;

public class Agente implements Runnable {
	private Random r = new Random();
	private Mesa m;

	public Agente(Mesa m) {
		this.m = m;
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(r.nextInt(200));
				m.nuevosIng(r.nextInt(3));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
