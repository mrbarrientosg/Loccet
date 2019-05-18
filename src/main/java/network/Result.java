package network;

public final class Result<T> {

    public static <T> Result<T> error(Throwable error) {
        if (error == null) throw new NullPointerException("error == null");
        return new Result<>(null, error);
    }

    public static <T> Result<T> value(T response) {
        return new Result<>(response, null);
    }

    private final T value;

    private final Throwable error;

    private Result(T value, Throwable error) {
        this.value = value;
        this.error = error;
    }

    public T value() {
        return value;
    }

    public Throwable error() {
        return error;
    }

    public boolean isError() {
        return error != null;
    }
}
