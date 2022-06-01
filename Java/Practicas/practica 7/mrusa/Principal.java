package mrusa;

public class Principal {

	public static void main(String[] args) {
		int N = 3;
		Coche cc = new Coche(N);
		Thread c = new Thread(cc);
		Thread[] pas = new Thread[10];
		for (int i = 0; i < pas.length; i++) {
			pas[i] = new Thread(new Pasajero(i, cc));
		}
		c.start();
		for (int i = 0; i < pas.length; i++) {
			pas[i].start();
		}
	}
}
