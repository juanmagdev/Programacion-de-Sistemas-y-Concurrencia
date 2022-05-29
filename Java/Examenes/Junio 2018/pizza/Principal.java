package pizza;

public class Principal {

	public static void main(String[] args) {
		final int NUM_ESTUDIANTES = 10;
		Mesa mesa = new Mesa();
		Pizzero p = new Pizzero(mesa);
		Estudiante[] est = new Estudiante[NUM_ESTUDIANTES];
		for (int i = 0; i < est.length; i++)
			est[i] = new Estudiante(i, mesa);
		p.start();
		for (int i = 0; i < est.length; i++)
			est[i].start();
	}

}
