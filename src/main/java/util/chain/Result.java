package util.chain;

public class Result {

    private boolean isSuccess;

    private Throwable error;

    private Result(boolean success, Throwable error) {
        this.isSuccess = success;
        this.error = error;
    }

    public static Result success() {
        return new Result(true, null);
    }

    public static Result error(Throwable error) {
        return new Result(false, error);
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public Throwable getError() {
        return error;
    }
}
