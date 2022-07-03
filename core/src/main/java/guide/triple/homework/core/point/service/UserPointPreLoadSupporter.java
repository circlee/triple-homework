package guide.triple.homework.core.point.service;

import guide.triple.homework.core.point.domain.UserPoint;
import guide.triple.homework.core.point.domain.UserPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Component
public class UserPointPreLoadSupporter {

    private final UserPointRepository userPointRepository;


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void preLoad(String userId) {

        try {
            userPointRepository.findById(userId)
                    .orElseGet(() -> userPointRepository.save(UserPoint.create(userId)));
        } catch (DataIntegrityViolationException ignore) {}
    }

}
