package guide.triple.homework.core.point.exception;

import guide.triple.homework.core.exception.DomainException;

public class NoSupportEventTypeHandlerException extends DomainException {
    public NoSupportEventTypeHandlerException(String message) {
        super(message);
    }
}
