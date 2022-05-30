package guarderiaMonitores;

//implementacion con monitores
public class Guarderia {
	private int numAdultos =0;
	private int numBebes =0; 
	
	/**
	 * Un bebe que quiere entrar en la guarderia llama a este metodo.
	 * Debe esperar hasta que sea seguro entrar, es decir, hasta que 
	 * cuado entre haya, al menos, 1 adulto por cada 3 bebes
	 * 
	 */
	public synchronized void entraBebe(int id) throws InterruptedException{
		while((numBebes + 1)> 3*numAdultos) wait();
		numBebes ++;
		System.out.println("Bebe " + id + " ha entrado 		[" + numAdultos + " , " + numBebes + " ]");
		

		notify();
	}
	/**
	 * Un bebe que quiere irse de la guarderia llama a este metodo * 
	 */
	public synchronized void saleBebe(int id) throws InterruptedException{
		numBebes --;
		System.out.println("Bebe " + id + " ha salido		[" + numAdultos + " , " + numBebes + " ]");
		notify();
	}
	/**
	 * Un adulto que quiere entrar en la guarderia llama a este metodo * 
	 */
	public synchronized void entraAdulto(int id) throws InterruptedException{
		numAdultos++;
		System.out.println("Adulto " + id + " ha entrado		[" + numAdultos + " , " + numBebes + " ]");
		notifyAll();
		
	}
	
	/**
	 * Un adulto que quiere irse  de la guarderia llama a este metodo.
	 * Debe esperar hasta que sea seguro salir, es decir, hasta que
	 * cuando se vaya haya, al menos, 1 adulto por cada 3 bebes
	 * 
	 */
	public synchronized void saleAdulto(int id) throws InterruptedException{
		while(numBebes> 3*(numAdultos-1)) wait();
		System.out.println("Adulto " + id + " ha salido		[" + numAdultos + " , " + numBebes + " ]");
		numAdultos --;

		notify();
		
	}

}
