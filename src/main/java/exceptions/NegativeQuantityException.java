package exceptions;

public class NegativeQuantityException extends Exception {

    public NegativeQuantityException() {
        super("La cantidad no puede ser un valor negativo.");
    }
}
