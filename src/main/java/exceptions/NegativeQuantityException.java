package exceptions;

/**
 * Excepcion cuando una cantidad es negativa
 */
public final class NegativeQuantityException extends Exception {

    public NegativeQuantityException() {
        super("La cantidad no puede ser un valor negativo.");
    }
}
