package pajaritos;

public class Principal {

	public static void main(String[] args) {
		Nido n = new Nido();
		Thread p = new Thread(new Papa(n, 0));
		Thread m = new Thread(new Papa(n, 1));
		Thread[] b = new Thread[10];
		for (int i = 0; i < b.length; i++) {
			b[i] = new Thread(new Bebe(n, i));
		}
		p.start();
		m.start();
		for (int i = 0; i < b.length; i++) {
			b[i].start();
		}
	}
}
