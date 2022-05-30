package pizza;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Mesa {
	
	private int trozos = 0;
	private ReentrantLock l = new ReentrantLock(true);
	private Condition pidePizza = l.newCondition();
	private Condition esperandoPizza = l.newCondition();
	private Condition pizzero = l.newCondition();
	private boolean pizzaPedida = false;
	private boolean pagado = false;

	
	/**
	 * 
	 * @param id
	 * El estudiante id quiere una ración de pizza. 
	 * Si hay una ración la coge y sigue estudiante.
	 * Si no hay y es el primero que se da cuenta de que la mesa está vacía
	 * llama al pizzero y
	 * espera hasta que traiga una nueva pizza. Cuando el pizzero trae la pizza
	 * espera hasta que el estudiante que le ha llamado le pague.
	 * Si no hay pizza y no es el primer que se da cuenta de que la mesa está vacía
	 * espera hasta que haya un trozo para él.
	 * @throws InterruptedException 
	 * 
	 */
	public void nuevaRacion(int id) throws InterruptedException{
		l.lock();
		try {
			if (trozos == 0 && !pizzaPedida) {
				pizzaPedida = true;
				System.out.println("El estudiante con id: " + id + " ha pedido una pizza");
				pizzero.signal();
				while (trozos == 0) {
					pidePizza.await();
				}
				System.out.println("El estudiante con id: " + id + " paga la pizza");
				pagado = true;
				pizzero.signal();
				trozos--;
				System.out.println("El estudiante con id: " + id + " se come un trozo de pizza. Trozos restantes: " + trozos);	
			} else if (trozos == 0) {
				esperandoPizza.await();
			} else {
				trozos--;
				System.out.println("El estudiante con id: " + id + " se come un trozo de pizza. Trozos restantes: " + trozos);
			}
		} finally {
			l.unlock();
		}
	}


	/**
	 * El pizzero entrega la pizza y espera hasta que le paguen para irse
	 * @throws InterruptedException 
	 */
	public void nuevoCliente() throws InterruptedException{
		l.lock();
		try {
			System.out.println("El pizzero entrega la pizza.");
			trozos = 8;
			pizzaPedida = false;
			pidePizza.signal();
			while (!pagado) {
				pizzero.await();
			}
			pagado = false;
			System.out.println("El pizzero ha sido pagado");
			esperandoPizza.signalAll();
			
		} finally {
			l.unlock();
		}
	}
	

/**
	 * El pizzero espera hasta que algún cliente le llama para hacer una pizza y
	 * llevársela a domicilio
	 * @throws InterruptedException 
	 */
	public void nuevaPizza() throws InterruptedException{
		l.lock();
		try {
			while (!pizzaPedida) {
				pizzero.await();
			}
			System.out.println("El pizzero ha hecho una pizza y la ha llevado a domicilio.");
		} finally {
			l.unlock();
		}
	}

}
