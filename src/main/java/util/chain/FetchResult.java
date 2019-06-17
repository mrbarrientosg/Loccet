package util.chain;

public class FetchResult {

    private boolean isSuccess;

    private Throwable error;

    private FetchResult(boolean success, Throwable error) {
        this.isSuccess = success;
        this.error = error;
    }

    public static FetchResult success() {
        return new FetchResult(true, null);
    }

    public static FetchResult error(Throwable error) {
        return new FetchResult(false, error);
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public Throwable getError() {
        return error;
    }
}
