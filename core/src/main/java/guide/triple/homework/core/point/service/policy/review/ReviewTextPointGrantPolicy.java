package guide.triple.homework.core.point.service.policy.review;

import guide.triple.homework.core.point.domain.PointGrantType;
import guide.triple.homework.core.point.service.dto.EventDTO;
import guide.triple.homework.core.point.service.dto.ReviewEventDTO;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

@Component
class ReviewTextPointGrantPolicy implements ReviewPointGrantPolicy {

    Predicate<String> CONTENT_PREDICATE = (content) -> content.trim().length() > 0;

    @Override
    public Optional<PointGrantType> getPointGrantType(ReviewEventDTO reviewEventDTO) {

        if(reviewEventDTO.isNotAnyMatchAction(EventDTO.EVENT_ACTION.ADD, EventDTO.EVENT_ACTION.MOD)) {
            return Optional.empty();
        }

        return Optional.ofNullable(reviewEventDTO)
                .map(ReviewEventDTO::getContent)
                .filter(Objects::nonNull)
                .filter(CONTENT_PREDICATE)
                .map((content) -> PointGrantType.REVIEW_TEXT);
    }
}
