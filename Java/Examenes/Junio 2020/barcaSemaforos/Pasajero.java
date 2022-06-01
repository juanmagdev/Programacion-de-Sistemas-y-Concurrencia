package barcaSemaforos;

import java.util.*;
public class Pasajero extends Thread{
	private int id;
	private int orilla;
	private Barca barca;
	private static Random r = new Random();
	public Pasajero(int id,int orilla,Barca barca){
		this.id = id;
		this.orilla = orilla;
		this.barca = barca;
	}
	
	public void run(){
		while (true){
			try {
				Thread.sleep(r.nextInt(1000));
				barca.subir(id, orilla);
				Thread.sleep(r.nextInt(500));
				orilla= barca.bajar(id);				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
