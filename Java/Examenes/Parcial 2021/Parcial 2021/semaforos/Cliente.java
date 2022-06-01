package semaforos;

public class Cliente extends Thread {
	private Cafeteria cafeteria;
	private int id;
	public Cliente(int i, Cafeteria caf) {
		id = i;
		cafeteria = caf;
	}
	public void run() {
		try {
		while(!isInterrupted()) {
			Thread.sleep(100);
			cafeteria.entrarCafeteria(id);
			cafeteria.esperarCafe(id);
			Thread.sleep(100); //busca el dinero para pagar
			cafeteria.pagarySalir(id);
			
		}
		}catch(InterruptedException e) {}
	}

}
