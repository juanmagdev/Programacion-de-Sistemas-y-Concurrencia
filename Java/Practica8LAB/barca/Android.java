package barca;

import java.util.*;

public class Android implements Runnable {

	private static Random r = new Random();
	private Barca b;
	private int id;

	public Android(Barca b, int id) {
		this.b = b;
		this.id = id;
	}

	public void run() {
		while (true) {
			try {
				b.android(id);
				Thread.sleep(r.nextInt(1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
