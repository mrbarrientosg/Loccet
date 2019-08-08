package exceptions;

/**
 * Excepcion cuando un objecto ya esta en el modelo
 */
public final class ObjectExistException extends Exception {

    public ObjectExistException(String message) {
        super(message);
    }

}
