package parada;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Parada parada = new Parada();
		Autobus bus = new Autobus(parada);
		Pasajero[] p = new Pasajero[100];
		for (int i=0;i<p.length; i++)
			p[i]=new Pasajero(parada,i);
		bus.start();
		for (int i=0;i<p.length; i++)
			p[i].start();
	}

}
