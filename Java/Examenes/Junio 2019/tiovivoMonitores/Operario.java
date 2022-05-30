package tiovivoMonitores;

public class Operario extends Thread {

	private Tiovivo t;

	public Operario(Tiovivo t) {
		this.t = t;
	}

	public void run() {
		try {
			while (true) {
				t.esperaLleno();
				Thread.sleep(500);
				t.finViaje();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
