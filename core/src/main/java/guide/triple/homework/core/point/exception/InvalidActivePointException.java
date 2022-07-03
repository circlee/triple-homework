package guide.triple.homework.core.point.exception;

import guide.triple.homework.core.exception.DomainException;

public class InvalidActivePointException extends DomainException {
    public InvalidActivePointException(String message) {
        super(message);
    }
}
