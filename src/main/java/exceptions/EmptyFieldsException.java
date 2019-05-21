package exceptions;

public class EmptyFieldsException extends Exception {

    public EmptyFieldsException() {
        super("Complete todos los campos");
    }
}
