package cl.loccet.util;

public interface ValidatorContext<T> {
    boolean validate(T object);
}
