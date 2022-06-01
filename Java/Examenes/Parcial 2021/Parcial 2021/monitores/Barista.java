package monitores;

public class Barista extends Thread {
	private Cafeteria cafeteria;

	public Barista(Cafeteria caf) {
		cafeteria = caf;
	}

	public void run() {
		try {
			while (!isInterrupted()) {
				cafeteria.prepararCafe();
			}
		} catch (InterruptedException e) {

		}
	}

}
