package cursoMonitores;

public class Curso {

	// Numero maximo de alumnos cursando simultaneamente la parte de iniciacion
	private final int MAX_ALUMNOS_INI = 10;

	// Numero de alumnos por grupo en la parte avanzada
	private final int ALUMNOS_AV = 3;

	private int numAlumosIni = 0;
	private int numAlumnosDisponibles = 0;
	private int numAlumnosTerminaron = 0;
	private boolean grupoTrabajando = false;

	// El alumno tendra que esperar si ya hay 10 alumnos cursando la parte de
	// iniciacion
	public synchronized void esperaPlazaIniciacion(int id) throws InterruptedException {
		// Espera si ya hay 10 alumnos cursando esta parte
		while (numAlumosIni > 10)
			wait();
		numAlumosIni++;

		// Mensaje a mostrar cuando el alumno pueda conectarse y cursar la parte de
		// iniciacion
		System.out.println(
				"	PARTE INICIACION: Alumno " + id + " cursa parte iniciacion					Hay" + numAlumosIni);
		notify();
	}

	// El alumno informa que ya ha terminado de cursar la parte de iniciacion
	public synchronized void finIniciacion(int id) throws InterruptedException {
		numAlumosIni--;
		// Mensaje a mostrar para indicar que el alumno ha terminado la parte de
		// principiantes
		System.out.println("	PARTE INICIACION: Alumno " + id + " termina parte iniciacion				Hay" + numAlumosIni);
		notify();

		// Libera la conexion para que otro alumno pueda usarla
	}

	/*
	 * El alumno tendra que esperar:
	 * - si ya hay un grupo realizando la parte avanzada
	 * - si todavia no estan los tres miembros del grupo conectados
	 */
	public synchronized void esperaPlazaAvanzado(int id) throws InterruptedException {
		// Espera a que no haya otro grupo realizando esta parte
		while (grupoTrabajando == true)
			wait();
		numAlumnosDisponibles++;
		boolean imprime = true;

		// Espera a que haya tres alumnos conectados
		while (numAlumnosDisponibles < 3) {

			if (imprime) {
				System.out.println("PARTE AVANZADA: Alumno " + id + " espera a que haya "
						+ (ALUMNOS_AV - numAlumnosDisponibles) + " alumnos");
				imprime = false;
			}
			wait();

		}

		// Mensaje a mostrar cuando el alumno pueda empezar a cursar la parte avanzada
		grupoTrabajando = true;
		System.out.println(
				"PARTE AVANZADA: Hay " + numAlumnosDisponibles + " alumnos. Alumno " + id + " empieza el proyecto");

		notifyAll();
	}

	/*
	 * El alumno:
	 * - informa que ya ha terminado de cursar la parte avanzada
	 * - espera hasta que los tres miembros del grupo hayan terminado su parte
	 */
	public synchronized void finAvanzado(int id) throws InterruptedException {
		// Espera a que los 3 alumnos terminen su parte avanzada
		numAlumnosTerminaron++;
		boolean imprime = true;
		while (numAlumnosTerminaron < 3) {
			// Mensaje a mostrar si el alumno tiene que esperar a que los otros miembros del
			// grupo terminen
			if (imprime) {
				System.out.println("PARTE AVANZADA: Alumno " + id + " termina su parte del proyecto. Espera al resto ["
						+ (ALUMNOS_AV - numAlumnosTerminaron) + "]");
				imprime = false;
			}
			wait();
		}
		// Mensaje a mostrar cuando los tres alumnos del grupo han terminado su parte
		System.out.println("PARTE AVANZADA: LOS " + ALUMNOS_AV + " ALUMNOS HAN TERMINADO EL CURSO		" + numAlumnosDisponibles);
		numAlumnosDisponibles = 0;
		grupoTrabajando = false;
		numAlumnosTerminaron = 0;
		notifyAll();
	}
}
