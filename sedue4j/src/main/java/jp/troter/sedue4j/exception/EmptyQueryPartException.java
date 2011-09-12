package jp.troter.sedue4j.exception;

public class EmptyQueryPartException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EmptyQueryPartException() {
        super();
    }

    public EmptyQueryPartException(String message) {
        super(message);
    }

    public EmptyQueryPartException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyQueryPartException(Throwable cause) {
        super(cause);
    }
}
