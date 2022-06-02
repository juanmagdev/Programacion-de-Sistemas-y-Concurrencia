package aseos;

import java.util.concurrent.Semaphore;

//Version injusta. Se podria hacer justa. 

public class Aseos {

	private Semaphore equipoLimpiezaTrabajando = new Semaphore(0, true);
	private Semaphore puedoEntrar = new Semaphore(1, true);
	private Semaphore mutex = new Semaphore(1, true);
	private int n = 0;	//inicialmente hay 0 clientes

	/**
	 * Utilizado por el cliente id cuando quiere entrar en los aseos
	 * CS Version injusta: El cliente espera si el equipo de limpieza está
	 * trabajando
	 * CS Version justa: El cliente espera si el equipo de limpieza está trabajando
	 * o
	 * está esperando para poder limpiar los aseos
	 * 
	 * @throws InterruptedException
	 * 
	 */
	public void entroAseo(int id) throws InterruptedException {

		puedoEntrar.acquire();
		mutex.acquire();

		n++;
		System.out.println("El cliente " + id + " ha entrado en el baño."
				+ "Clientes en el aseo: " + n);
		
		mutex.release();
		puedoEntrar.release();

	}

	/**
	 * Utilizado por el cliente id cuando sale de los aseos
	 * 
	 * @throws InterruptedException
	 * 
	 */
	public void salgoAseo(int id) throws InterruptedException {

		mutex.acquire();
		n--;

		System.out.println("El cliente " + id + " ha salido del baño."
				+ "Clientes en el aseo: " + n);

		if (n==0) equipoLimpiezaTrabajando.release();	//si hay 0 personas, le damos permiso al equipo de limpieza para que pueda trabajar
		
		mutex.release();

	}

	/**
	 * Utilizado por el Equipo de Limpieza cuando quiere entrar en los aseos
	 * CS: El equipo de trabajo está solo en los aseos, es decir, espera hasta que
	 * no
	 * haya ningún cliente.
	 * 
	 * @throws InterruptedException
	 * 
	 */
	public void entraEquipoLimpieza() throws InterruptedException {

		equipoLimpiezaTrabajando.acquire();

		System.out.println("El equipo de limpieza está trabajando.");

		puedoEntrar.acquire();	//quitamos permisos para que no entren clientes

	}

	/**
	 * Utilizado por el Equipo de Limpieza cuando sale de los aseos
	 * 
	 * @throws InterruptedException
	 * 
	 * 
	 */
	public void saleEquipoLimpieza() throws InterruptedException {
		System.out.println("El equipo de limpieza ha terminado.");

		puedoEntrar.release();
		equipoLimpiezaTrabajando.release();
		

	}
}
