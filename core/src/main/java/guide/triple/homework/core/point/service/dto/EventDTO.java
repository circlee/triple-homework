package guide.triple.homework.core.point.service.dto;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public abstract class EventDTO {

    private final Long eventId;

    private final String userId;

    abstract ReviewEventDTO.EVENT_TYPE getType();

    enum EVENT_TYPE {
        REVIEW
    }
}
