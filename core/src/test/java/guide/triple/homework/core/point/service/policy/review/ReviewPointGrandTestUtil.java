package guide.triple.homework.core.point.service.policy.review;

import guide.triple.homework.core.point.service.dto.EventDTO;
import guide.triple.homework.core.point.service.dto.ReviewEventDTO;

import java.util.List;

public class ReviewPointGrandTestUtil {

    private static final Long DEFAULT_EVENT_ID = 1L;
    private static final String DEFAULT_REVIEW_ID = "1234";
    private static final String DEFAULT_USER_ID = "1234";
    private static final String DEFAULT_PLACE_ID = "1234";


    public static ReviewEventDTO create(String content, List<String> photoIds) {
        return create(EventDTO.EVENT_ACTION.ADD, content, photoIds);
    }

    public static ReviewEventDTO create(EventDTO.EVENT_ACTION eventAction, String content, List<String> photoIds) {

        return ReviewEventDTO.builder()
                .eventId(DEFAULT_EVENT_ID)
                .reviewId(DEFAULT_REVIEW_ID)
                .eventAction(eventAction)
                .placeId(DEFAULT_PLACE_ID)
                .userId(DEFAULT_USER_ID)
                .content(content)
                .attachedPhotoIds(photoIds)
                .build();
    }
}
