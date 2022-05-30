package babuinos.babuinosMonitores;


import java.util.*;
public class BabuinoNS extends Thread{
	
	private Cuerda cuerda;
	private int id;
	private static Random r = new Random();
	
	public BabuinoNS(int id,Cuerda c){
		cuerda = c;
		this.id = id;
	}

	
	public void run(){
		try {
			cuerda.entraDireccionNS(id);
			Thread.sleep(r.nextInt(1000));
			cuerda.saleDireccionNS(id);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}