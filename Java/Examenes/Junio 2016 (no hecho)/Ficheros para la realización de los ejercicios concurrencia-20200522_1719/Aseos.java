package aseos;


public class Aseos {
	
	
	/**
	 * Utilizado por el cliente id cuando quiere entrar en los aseos
	 * CS Version injusta: El cliente espera si el equipo de limpieza está trabajando
	 * CS Version justa: El cliente espera si el equipo de limpieza está trabajando o
	 * está esperando para poder limpiar los aseos
	 * 
	 */
	public void entroAseo(int id){
	
	}

	/**
	 * Utilizado por el cliente id cuando sale de los aseos
	 * 
	 */
	public void salgoAseo(int id){
	
	}
	
	/**
	 * Utilizado por el Equipo de Limpieza cuando quiere entrar en los aseos 
	 * CS: El equipo de trabajo está solo en los aseos, es decir, espera hasta que no
	 * haya ningún cliente.
	 * 
	 */
    public void entraEquipoLimpieza(){
		
	}
    
    /**
	 * Utilizado por el Equipo de Limpieza cuando  sale de los aseos 
	 * 
	 * 
	 */
    public void saleEquipoLimpieza(){
    	
	}
}
