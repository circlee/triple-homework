package guide.triple.homework.core.point.service.policy.review;

import guide.triple.homework.core.point.domain.PointGrantType;
import guide.triple.homework.core.point.service.dto.EventDTO;
import guide.triple.homework.core.point.service.dto.ReviewEventDTO;

import java.util.Optional;

public interface ReviewPointGrantPolicy {

    boolean isSupportEventAction(EventDTO.EVENT_ACTION eventAction);
    Optional<PointGrantType> getPointGrantType(ReviewEventDTO reviewEventDTO);

}
