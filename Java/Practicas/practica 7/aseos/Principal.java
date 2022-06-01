package aseos;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int NUM_CLIENTES = 4;

		Aseos aseo = new Aseos();
		Thread[] cliente = new Thread[NUM_CLIENTES];
		for (int i = 0; i < cliente.length; i++) {
			cliente[i] = new Thread(new Cliente(i, aseo));
		}
		Thread equipo = new Thread(new EquipoLimpieza(aseo));
		equipo.start();
		for (int i = 0; i < cliente.length; i++) {
			cliente[i].start();
		}
	}

}
