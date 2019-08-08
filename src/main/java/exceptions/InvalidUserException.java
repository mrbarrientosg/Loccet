package exceptions;

/**
 * Excepcion cuando las credenciales de un usuario son incorrectas
 */
public final class InvalidUserException extends Exception {
    public InvalidUserException() {
        super("Usuario o constraseña incorrecta");
    }
}
