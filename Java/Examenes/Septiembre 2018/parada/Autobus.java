package parada;



public class Autobus extends Thread{
	
	private Parada parada;
	
	public Autobus(Parada parada){
		this.parada = parada;
	}

	public void run(){

		while (true){
			try {
				Thread.sleep(500);
				parada.llegoParada();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
