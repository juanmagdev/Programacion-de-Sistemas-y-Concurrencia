package barca;

public class Principal {

	public static void main(String[] args) throws InterruptedException {

		Thread[] android = new Thread[4];
		Thread[] iphone = new Thread[4];
		Barca b = new Barca();

		for (int i = 0; i < android.length; i++) {
			android[i] = new Thread(new Android(b, i));
		}

		for (int i = 0; i < iphone.length; i++) {
			iphone[i] = new Thread(new IPhone(b, i));
		}

		System.out.println("INICIO DEL PROGRAMA");
		b.start();

		for (int i = 0; i < android.length; i++) {
			android[i].start();
		}

		for (int i = 0; i < iphone.length; i++) {
			iphone[i].start();
		}
		iphone[0].join();
	}
}
