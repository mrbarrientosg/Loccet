package exceptions;

/**
 * Excepcion para cuando un rango de fechas es incorrecto
 */
public final class DateRangeException extends Exception {

    public DateRangeException(String message) {
        super(message);
    }
}
