package guide.triple.homework.core.point.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserReviewPointDetailRepository extends JpaRepository<UserReviewPointDetail, String> {

    List<UserReviewPointDetail> findAllByUserIdAndActive(String userId, boolean active);

    List<UserReviewPointDetail> findAllByUserIdAndReviewIdAndActive(String userId, String reviewId, boolean active);
}
