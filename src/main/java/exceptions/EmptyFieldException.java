package exceptions;

/**
 * Excepcion para un campo vacio y este es requerido
 */
public final class EmptyFieldException extends Exception {

    private String fieldName;

    public EmptyFieldException(String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
    }

    public EmptyFieldException(String fieldName) {
        super(fieldName + " incompleto");
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
