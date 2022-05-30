package parejas;

public class Principal {
	public static void main(String[] args) {
		Sala sala = new Sala();
		Thread[] h = new Thread[10];
		Thread[] m = new Thread[10];
		for (int i = 0; i < h.length; i++)
			h[i] = new Thread(new Hombre(i, sala));

		for (int i = 0; i < m.length; i++)
			m[i] = new Thread(new Mujer(i, sala));

		for (int i = 0; i < h.length; i++)
			h[i].start();

		for (int i = 0; i < m.length; i++)
			m[i].start();
	}

}
