package guide.triple.homework.core.point.exception;

import guide.triple.homework.core.exception.DomainException;

public class UserPointNotFoundException extends DomainException {
    public UserPointNotFoundException(String userId) {
        super("can not find userPoint : " + userId);
    }
}
