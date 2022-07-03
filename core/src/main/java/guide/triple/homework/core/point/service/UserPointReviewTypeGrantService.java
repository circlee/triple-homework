package guide.triple.homework.core.point.service;


import guide.triple.homework.core.point.domain.PointGrantType;
import guide.triple.homework.core.point.domain.UserReviewPointDetail;
import guide.triple.homework.core.point.domain.UserReviewPointDetailRepository;
import guide.triple.homework.core.point.service.dto.EventDTO;
import guide.triple.homework.core.point.service.dto.ReviewEventDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
class UserPointReviewTypeGrantService implements UserPointTypeGrantService<ReviewEventDTO> {


    private final UserReviewPointDetailRepository userReviewPointDetailRepository;

    @Override
    public EventDTO.EVENT_TYPE supportType() {
        return EventDTO.EVENT_TYPE.REVIEW;
    }

    @Override
    public BigDecimal userPointGrant(ReviewEventDTO eventDto, Set<PointGrantType> tobePointGrantTypeSet) {

        String userId = eventDto.getUserId();
        String placeId = eventDto.getPlaceId();
        String reviewId = eventDto.getReviewId();
        Long eventId = eventDto.getEventId();

        List<UserReviewPointDetail> currentActiveDetails = userReviewPointDetailRepository.findAllByUserIdAndReviewIdAndActive(userId, reviewId, true);


        if(EventDTO.EVENT_ACTION.DELETE == eventDto.getEventAction()) {
            return revoke(currentActiveDetails, eventId);
        }

        // revoke
        List<UserReviewPointDetail> revokeTargetDetails = currentActiveDetails.stream()
                .filter(detail -> detail.getPointGrantType() != PointGrantType.FIRST_REVIEW_IN_PLACE)
                .filter(detail -> !tobePointGrantTypeSet.contains(detail.getPointGrantType()))
                .collect(Collectors.toList());

        BigDecimal revokePoint = revoke(revokeTargetDetails, eventId);


        // grant
        Set<PointGrantType> currentGrantTypes = currentActiveDetails.stream()
                .map(UserReviewPointDetail::getPointGrantType)
                .collect(Collectors.toSet());

        BigDecimal grantPoint = tobePointGrantTypeSet.stream()
                .filter(pointGrantType -> !currentGrantTypes.contains(pointGrantType))
                .map(pointGrantType -> this.grant(pointGrantType, userId, placeId, reviewId, eventId))
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        return revokePoint.add(grantPoint);

    }

    private BigDecimal grant(PointGrantType pointGrantType, String userId, String placeId, String reviewId, Long eventId) {

        UserReviewPointDetail revokeDetail = UserReviewPointDetail.createGrant(userId, pointGrantType, placeId, reviewId, eventId);
        userReviewPointDetailRepository.save(revokeDetail);

        return revokeDetail.getPointValue();
    }

    private BigDecimal revoke(List<UserReviewPointDetail> revokeTargetDetails, Long eventId) {
        return revokeTargetDetails.stream()
                .map(detail -> this.revoke(detail, eventId))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal revoke(UserReviewPointDetail detail, Long eventId) {

        // deactivate
        detail.changeDeactivate();

        // insert revoke row
        UserReviewPointDetail revokeDetail = detail.createRevokeDetail(eventId);
        userReviewPointDetailRepository.save(revokeDetail);

        return revokeDetail.getPointValue().negate();
    }




}
