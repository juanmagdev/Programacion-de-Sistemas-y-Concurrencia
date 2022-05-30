package pizza;

public class Mesa {

	private int nPorcionesDisponibles = 8; // suponemos que una pizza se divide en 8 porciones

	/**
	 * 
	 * @param id
	 *           El estudiante id quiere una ración de pizza.
	 *           Si hay una ración la coge y sigue estudiando.
	 *           Si no hay y es el primero que se da cuenta de que la mesa está
	 *           vacía
	 *           llama al pizzero y
	 *           espera hasta que traiga una nueva pizza. Cuando el pizzero trae la
	 *           pizza
	 *           espera hasta que el estudiante que le ha llamado le pague.
	 *           Si no hay pizza y no es el primer que se da cuenta de que la mesa
	 *           está vacía
	 *           espera hasta que haya un trozo para él.
	 * @throws InterruptedException
	 * 
	 */
	public synchronized void nuevaRacion(int id) throws InterruptedException {

		while (nPorcionesDisponibles == 0)
			wait();
		
		if (nPorcionesDisponibles ==8) {
			System.out.println("		El estudiante " + id + " ha pedido una pizza");
			
		}
		nPorcionesDisponibles--;
		System.out.println("El estudiante " + id + " ha cogido una porcion. Hay " + nPorcionesDisponibles + " porciones disponibles");

		notifyAll();
	}

	/**
	 * El pizzero entrega la pizza y espera hasta que le paguen para irse
	 * 
	 * @throws InterruptedException
	 */
	public synchronized void nuevoCliente() throws InterruptedException {
		while (nPorcionesDisponibles != 0)
			wait();
		
		System.out.println("El pizzero ha recibido un nuevo pedido.");
	}

	/**
	 * El pizzero espera hasta que algún cliente le llama para hacer una pizza y
	 * llevársela a domicilio
	 * 
	 * @throws InterruptedException
	 */
	public synchronized void nuevaPizza() throws InterruptedException {
		nPorcionesDisponibles = 8;
		System.out.println("Hay una nueva pizza. Hay un total de " + nPorcionesDisponibles + " porciones disponibles");
		notifyAll();
	}

}
