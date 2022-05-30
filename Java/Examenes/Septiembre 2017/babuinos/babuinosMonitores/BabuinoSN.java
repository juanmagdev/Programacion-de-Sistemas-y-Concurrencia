package babuinos.babuinosMonitores;

import java.util.*;
public class BabuinoSN extends Thread{
	
	private Cuerda cuerda;
	private int id;
	private static Random r = new Random();
	
	public BabuinoSN(int id,Cuerda c){
		cuerda = c;
		this.id = id;
	}

	
	public void run(){
		try {
			cuerda.entraDireccionSN(id);
			Thread.sleep(r.nextInt(1000));
			cuerda.saleDireccionSN(id);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
