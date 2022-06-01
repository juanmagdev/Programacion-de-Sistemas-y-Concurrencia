package fumadores;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Mesa m = new Mesa();
		Thread a = new Thread(new Agente(m));
		Thread[] f = new Thread[3];
		for (int i = 0; i < f.length; i++)
			f[i] = new Thread(new Fumador(m, i));
		a.start();
		for (int i = 0; i < f.length; i++)
			f[i].start();
	}

}
