package tiovivoSemaforos;

import java.util.Random;

public class Pasajero extends Thread {

	private int id;
	private Tiovivo t;
	private Random r;
	
	public Pasajero(int id, Tiovivo t)
	{
		this.id = id;
		this.t = t;
		r = new Random();
	}
	
	public void run()
	{
		boolean fin = false;
		while(!this.isInterrupted() && !fin)
		{
			try {
			Thread.sleep(10+r.nextInt(50));	
			t.subir(id);
			t.bajar(id);
			
			}catch(InterruptedException e)
			{
				fin = true;
			}
		}
	}
}
