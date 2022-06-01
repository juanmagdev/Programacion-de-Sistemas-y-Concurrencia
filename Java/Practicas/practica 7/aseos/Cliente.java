package aseos;

import java.util.Random;

public class Cliente implements Runnable {

	private int id;
	private Aseos aseo;
	private static Random r = new Random();

	public Cliente(int id, Aseos aseo) {
		this.id = id;
		this.aseo = aseo;
	}

	public void run() {
		while (true) {

			try {
				Thread.sleep(r.nextInt(1000));
				aseo.entroAseo(id);
				Thread.sleep(r.nextInt(1000));
				aseo.salgoAseo(id);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
