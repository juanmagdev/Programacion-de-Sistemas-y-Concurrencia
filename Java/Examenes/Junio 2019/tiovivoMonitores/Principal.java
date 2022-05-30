package tiovivoMonitores;

public class Principal {

	public static void main(String[] args) {
		Tiovivo t = new Tiovivo(5);
		Operario o = new Operario(t);
		Pasajero[] personas = new Pasajero[7];
		for (int i = 0; i < 7; i++) {
			personas[i] = new Pasajero(i, t);
		}

		for (int i = 0; i < 7; i++) {
			personas[i].start();
		}
		o.start();
	}
}
