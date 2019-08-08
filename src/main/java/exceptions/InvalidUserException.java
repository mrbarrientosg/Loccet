package exceptions;

public final class InvalidUserException extends Exception {
    public InvalidUserException() {
        super("Usuario o constraseña incorrecta");
    }
}
