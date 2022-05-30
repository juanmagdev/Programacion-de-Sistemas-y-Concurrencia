package barca;

import java.util.Random;

public class IPhone implements Runnable {

	private static Random r = new Random();
	private Barca b;
	private int id;

	public IPhone(Barca b, int id) {
		this.b = b;
		this.id = id;
	}

	public void run() {
		while (true) {
			try {
				b.iphone(id);
				Thread.sleep(r.nextInt(1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
