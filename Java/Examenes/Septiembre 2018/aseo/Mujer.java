package aseo;

import java.util.Random;

public class Mujer extends Thread{
	private Aseo aseo;
	private int id;
	private static Random r = new Random();
	
	public Mujer(Aseo aseo,int id){
		this.id = id;
		this.aseo = aseo;
	}

	
	public void run(){
		while (true){
			try {
				Thread.sleep(5000);
				aseo.llegaMujer(id);
				Thread.sleep(r.nextInt(500));
				aseo.saleMujer(id);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
