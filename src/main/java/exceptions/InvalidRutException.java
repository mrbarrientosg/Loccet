package exceptions;

public class InvalidRutException extends Exception {

    public InvalidRutException() {
        super("Rut incorrecto, valide que sea correcto");
    }
}
