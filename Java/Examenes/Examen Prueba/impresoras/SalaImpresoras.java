package impresoras;

public interface SalaImpresoras {
    public int quieroImpresora(int id) throws InterruptedException;

    public void devuelvoImpresora(int id, int n) throws InterruptedException;
}