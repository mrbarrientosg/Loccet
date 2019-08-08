package exceptions;

public final class InvalidRutException extends Exception {

    public InvalidRutException() {
        super("Rut incorrecto, valide que sea correcto");
    }
}
