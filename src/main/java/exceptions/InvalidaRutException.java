package exceptions;

public class InvalidaRutException extends Exception {

    public InvalidaRutException() {
        super("Rut incorrecto, valide que sea correcto");
    }
}
