package impresoras;

public class Principal {

    public static void main(String[] args) {

        int N = 3;      //numero impresoras
        int NC = 20;    //numero clientes
        SalaImpresoras sala = new SalaImpresorasS(N); // FALTA. cambiar para modificar la implementación de las operaciones
        Thread[] cliente = new Thread[NC];
        for (int i = 0; i < cliente.length; i++) {
            cliente[i] = new Thread(new Cliente(sala, i));
        }
        for (int i = 0; i < cliente.length; i++) {
            cliente[i].start();
        }
    }

}
