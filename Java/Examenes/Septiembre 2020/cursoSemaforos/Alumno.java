package cursoSemaforos;

public class Alumno extends Thread{
	private java.util.Random rnd = new java.util.Random();
	private int id;
	private Curso curso;
	
	public Alumno(int id, Curso curso){
		this.id = id;
		this.curso = curso;
	}
	
	public void run(){
		try {
			//El alumno espera un tiempo aleatorio
			Thread.sleep(id*rnd.nextInt(200));
			
			//El alumno espera para tener una plaza en la parte de iniciacion
			curso.esperaPlazaIniciacion(id);
			//El alumno cursa la parte de iniciacion, cada uno a su ritmo
			Thread.sleep(id*rnd.nextInt(100));
			//El alumno informa que ha terminado la parte de iniciacion y libera su conexion
			curso.finIniciacion(id);

			//El alumno espera a que haya plaza en la parte avanzada y que se forme el grupo de 3 miembros
			curso.esperaPlazaAvanzado(id);
			//El alumno cursa la parte avanzada
			Thread.sleep(id*rnd.nextInt(500));
			//El alumno informa que ha terminado su parte y espera a que todos los miembros del grupo terminen
			curso.finAvanzado(id);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
