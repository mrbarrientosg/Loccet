package exceptions;

public class InvalidUserException extends Exception {

    public InvalidUserException() {
        super("Usuario o constraseña incorrecta");
    }
}
