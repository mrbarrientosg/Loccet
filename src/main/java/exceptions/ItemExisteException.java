package exceptions;

public class ItemExisteException extends Exception {

    public ItemExisteException() {
        super("El item u objecto ya existe en la colección.");
    }
}
