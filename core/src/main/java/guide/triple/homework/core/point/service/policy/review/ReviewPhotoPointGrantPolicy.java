package guide.triple.homework.core.point.service.policy.review;

import guide.triple.homework.core.point.domain.PointGrantType;
import guide.triple.homework.core.point.service.dto.EventDTO;
import guide.triple.homework.core.point.service.dto.ReviewEventDTO;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

@Component
class ReviewPhotoPointGrantPolicy implements ReviewPointGrantPolicy {

    Predicate<List<String>> PHOTO_IDS_PREDICATE = (ids) -> {
        return ids.stream()
                .filter(Objects::nonNull)
                .filter(StringUtils::hasText)
                .findAny()
                .isPresent();
    };

    @Override
    public Optional<PointGrantType> getPointGrantType(ReviewEventDTO reviewEventDTO) {

        if(reviewEventDTO.isNotAnyMatchAction(EventDTO.EVENT_ACTION.ADD, EventDTO.EVENT_ACTION.MOD)) {
            return Optional.empty();
        }

        return Optional.ofNullable(reviewEventDTO)
                .map(ReviewEventDTO::getAttachedPhotoIds)
                .filter(Objects::nonNull)
                .filter(PHOTO_IDS_PREDICATE)
                .map((content) -> PointGrantType.REVIEW_PHOTO);
    }
}
