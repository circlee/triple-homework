package guide.triple.homework.core.exception;

public class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }

    public DomainException(Throwable cause) {
        super(cause);
    }
}
