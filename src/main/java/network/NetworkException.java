package network;

public final class NetworkException extends Exception {

    private final int code;

    public NetworkException(int code, String message) {
        super(message);
        this.code = code;
    }

    public NetworkException(int code) {
        super();
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
