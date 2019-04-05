package cl.loccet.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public final class Validator<T> {

    private final List<T> objects;

    private final ValidationResult result;

    private Validator(final T data) {
        objects = new ArrayList<>();
        result = new ValidationResult();
        objects.add(data);
    }

    private Validator(T... data) {
        objects = new ArrayList<>(Arrays.asList(data));
        result = new ValidationResult();
    }

    public static <T> Validator<T> of(T data) {
        return new Validator<>(data);
    }

    public static <T> Validator<T> of(T... data) {
        return new Validator<>(data);
    }

    private void check(Boolean validationCase, String errorMessage) {
        if (!validationCase) {
            result.setValid(false);
            result.addMessage(errorMessage);
            return;
        }

        result.setValid(true);
    }

    public Validator<T> validate(ValidatorContext<T> validation, String message) {
        objects.forEach(object -> {
            check(validation.validate(object), message);
        });
        return this;
    }

    public <U> Validator<T> validate(Function<T, U> projection, ValidatorContext<U> validation, String message) {
        return validate(projection.andThen(validation::validate)::apply, message);
    }


    public final ValidationResult result() {
        return result;
    }

}
