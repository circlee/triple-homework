package guide.triple.homework.core.point.service.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public class ReviewEventDTO extends EventDTO{

    private final String reviewId;

    private final String placeId;

    private final String content;

    private final List<String> attachedPhotoIds;


    @Override
    EVENT_TYPE getType() {
        return EVENT_TYPE.REVIEW;
    }
}
