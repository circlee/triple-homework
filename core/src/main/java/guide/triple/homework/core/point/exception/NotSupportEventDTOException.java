package guide.triple.homework.core.point.exception;

import guide.triple.homework.core.exception.DomainException;

public class NotSupportEventDTOException extends DomainException {
    public NotSupportEventDTOException(String message) {
        super(message);
    }
}
