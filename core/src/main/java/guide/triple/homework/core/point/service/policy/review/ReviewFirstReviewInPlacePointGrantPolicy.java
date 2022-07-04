package guide.triple.homework.core.point.service.policy.review;

import guide.triple.homework.core.point.domain.PointGrantType;
import guide.triple.homework.core.point.domain.UserReviewPointDetailRepository;
import guide.triple.homework.core.point.service.dto.EventDTO;
import guide.triple.homework.core.point.service.dto.ReviewEventDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Todo: Review Domain 으로부터 최초 리뷰 여부를 받아서 처리하는게 좋지 않을까 싶습니다. or Review Domain 조회
 */
@RequiredArgsConstructor
@Component
class ReviewFirstReviewInPlacePointGrantPolicy implements ReviewPointGrantPolicy {

    private final UserReviewPointDetailRepository userReviewPointDetailRepository;

    @Override
    public boolean isSupportEventAction(EventDTO.EVENT_ACTION eventAction) {
        return EventDTO.EVENT_ACTION.ADD == eventAction;
    }
    @Override
    public Optional<PointGrantType> getPointGrantType(ReviewEventDTO reviewEventDTO) {


        long count = userReviewPointDetailRepository.countByPlaceIdAndActive(reviewEventDTO.getPlaceId(), true);

        if(count > 0) {
            return Optional.empty();
        }

        return Optional.of(PointGrantType.FIRST_REVIEW_IN_PLACE);
    }
}
