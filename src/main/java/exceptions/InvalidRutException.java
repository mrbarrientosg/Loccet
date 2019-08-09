package exceptions;

/**
 * Excepcion cuando un rut es invalido
 */
public final class InvalidRutException extends Exception {

    public InvalidRutException() {
        super("Rut incorrecto, valide que sea correcto");
    }
}
