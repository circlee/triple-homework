package guide.triple.homework.core.orchestration.exception;

import guide.triple.homework.core.exception.DomainException;

public class NotFoundSupporterException extends DomainException {
    public NotFoundSupporterException(String message) {
        super(message);
    }
}
