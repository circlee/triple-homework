package guide.triple.homework.core.orchestration.dto.converter;

import guide.triple.homework.core.orchestration.dto.EventRawSupportDTO;
import guide.triple.homework.core.point.service.dto.EventDTO;
import guide.triple.homework.core.point.service.dto.ReviewEventDTO;
import org.springframework.stereotype.Component;

@Component
public class PointReviewEventDtoConverter implements PointEventDtoConverter<ReviewEventDTO> {
    @Override
    public EventRawSupportDTO.EVENT_TYPE supportType() {
        return EventRawSupportDTO.EVENT_TYPE.REVIEW;
    }

    @Override
    public ReviewEventDTO convert(EventRawSupportDTO eventRawSupportDTO, Long eventId) {
        return ReviewEventDTO.builder()
                .eventId(eventId)
                .eventAction(EventDTO.EVENT_ACTION.valueOf(eventRawSupportDTO.getAction().name()))
                .reviewId(eventRawSupportDTO.getReviewId())
                .userId(eventRawSupportDTO.getUserId())
                .placeId(eventRawSupportDTO.getPlaceId())
                .content(eventRawSupportDTO.getContent())
                .attachedPhotoIds(eventRawSupportDTO.getAttachedPhotoIds())
                .build();
    }
}
