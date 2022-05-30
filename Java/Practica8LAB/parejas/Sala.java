package parejas;

public class Sala {

	/**
	 * un hombre llega a la sala para formar una pareja si ya hay otra mujer en la
	 * sala o si a�n no hay un hombre
	 * 
	 * @throws InterruptedException
	 */
	public synchronized void llegaHombre(int id) throws InterruptedException {

		System.out.println("El hombre " + id + " espera en la sala.");

		System.out.println("El hombre " + id + " ha tenido una cita.");

		System.out.println("El hombre " + id + " sale de la sala.");

	}

	/**
	 * una mujer llega a la sala para formar una pareja debe esperar si ya hay otra
	 * mujer en la sala o si aún no hay un hombre
	 * 
	 * @throws InterruptedException
	 */
	public synchronized void llegaMujer(int id) throws InterruptedException {

		System.out.println("La mujer " + id + " espera en la sala.");

		System.out.println("La mujer  " + id + " ha tenido una cita.");

		System.out.println("La mujer " + id + " sale de la sala.");

	}
}
