package cl.loccet.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public final class Validator<T> {

    private final T data;

    private final List<String> messages;

    private Boolean valid;

    private Validator(final T data) {
        this.data = data;
        valid = false;
        messages = new ArrayList<>();
    }

    public static <T> Validator<T> of(T data) {
        return new Validator<>(data);
    }

    private void check(Boolean validationCase, String errorMessage) {
        if (!validationCase) {
            this.valid = false;
            messages.add(errorMessage);
        }
    }

    public Validator<T> on(Predicate<T> validation, String message) {
        check(validation.test(data), message);
        return this;
    }

    public <U> Validator<T> on(Function<T, U> projection, Predicate<U> validation, String message) {
        return on(projection.andThen(validation::test)::apply, message);
    }

    public Boolean isValid() {
        return valid;
    }


}
