package guide.triple.homework.core.point.service.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Arrays;

@Getter
@SuperBuilder
public abstract class EventDTO {

    private final Long eventId;

    private final String userId;

    private final EVENT_ACTION eventAction;

    public abstract ReviewEventDTO.EVENT_TYPE getType();

    public enum EVENT_TYPE {
        REVIEW
    }

    public enum EVENT_ACTION {
        ADD,
        MOD,
        DELETE
    }

    public boolean isAnyMatchAction(EVENT_ACTION... eventActions) {
        if(eventActions == null) {
            return false;
        }

        return Arrays.stream(eventActions)
                .anyMatch(this.eventAction::equals);
    }

    public boolean isNotAnyMatchAction(EVENT_ACTION... eventActions) {
        return !isAnyMatchAction(eventActions);
    }
}
