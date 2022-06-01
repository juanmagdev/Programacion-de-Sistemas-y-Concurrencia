package aseo;

public class Principal {
	
	public static void main(String[] str){
		Aseo aseo = new Aseo();
		Hombre[] h = new Hombre[10];
		Mujer[] m = new Mujer[10];
		for (int i = 0; i<h.length; i++){
			h[i] = new Hombre(aseo,i);
			m[i] = new Mujer(aseo,i);
		}
		for (int i = 0; i<h.length; i++){
			h[i].start();
			m[i].start();
		}
	}

}
