package guide.triple.homework.core.point.service;

import guide.triple.homework.core.point.domain.PointGrantType;
import guide.triple.homework.core.point.domain.UserPoint;
import guide.triple.homework.core.point.domain.UserPointRepository;
import guide.triple.homework.core.point.domain.UserReviewPointDetailRepository;
import guide.triple.homework.core.point.exception.UserPointNotFoundException;
import guide.triple.homework.core.point.service.dto.EventDTO;
import guide.triple.homework.core.point.service.policy.PointGrantPolicySupporters;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Set;


@RequiredArgsConstructor
@Component
public class UserPointOverallService {

    private final UserPointPreLoadSupporter userPointPreLoadSupporter;
    private final UserPointRepository userPointRepository;
    private final UserReviewPointDetailRepository userReviewPointDetailRepository;
    private final PointGrantPolicySupporters pointGrantPolicySupporters;
    private final UserPointTypeGrantServiceSupporter userPointTypeGrantServiceSupporter;



    @Transactional
    public void addUserPointByEvent(EventDTO eventDTO) {

        userPointPreLoadSupporter.preLoad(eventDTO.getUserId());

        Set<PointGrantType> tobePointGrantTypeSet = pointGrantPolicySupporters.getPointGrantTypes(eventDTO);

        userPointRepository.findByUserIdWithLock(eventDTO.getUserId())
                        .ifPresent(userPoint -> {
                            BigDecimal grantedPoint = userPointTypeGrantServiceSupporter.userPointGrant(eventDTO, tobePointGrantTypeSet);

                            userPoint.updateActivePointWithGrantedValue(grantedPoint);
                        });
    }

    @Transactional(readOnly = true)
    public BigDecimal getUserActivePoint(String userId) {

        return userPointRepository.findById(userId)
                .map(UserPoint::getActivePoint)
                .orElseThrow(() -> new UserPointNotFoundException(userId));
    }

}
