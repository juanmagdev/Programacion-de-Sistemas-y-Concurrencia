package cursoMonitores;

public class Principal {

	public static void main(String[] args) {
		//numero total de alumnos que realizaran el curso
		final int NUMALUMNOS= 6; 
		
		Curso curso = new Curso();
		
		//Crea NUMALUMNOS alumnos
		Alumno alumnos [] = new Alumno[NUMALUMNOS];
		for (int i = 0; i< NUMALUMNOS; i++){
			alumnos[i] = new Alumno(i,curso); 
		}

		//Inicia la ejecución de los alumnos (hebras)
		for (int i = 0; i< NUMALUMNOS; i++){
			alumnos[i].start();
		}
		
		//Espera a que todos los alumnos finalicen el curso y termina
		try {
			for (int i = 0; i< NUMALUMNOS; i++)
				alumnos[i].join();
			
		} catch (InterruptedException e) {
				e.printStackTrace();
		}
	}
}
