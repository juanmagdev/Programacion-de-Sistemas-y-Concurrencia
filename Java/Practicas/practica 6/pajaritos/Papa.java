package pajaritos;

import java.util.*;

public class Papa implements Runnable {

	private static Random r = new Random();
	private Nido n;
	private int id;

	public Papa(Nido n, int id) {
		this.id = id;
		this.n = n;
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(r.nextInt(200));
				n.nuevoBichito(id);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
