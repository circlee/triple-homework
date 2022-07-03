package guide.triple.homework.core.point.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface UserPointRepository extends JpaRepository<UserPoint, String> {

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query("select up from UserPoint up where up.userId = :userId")
    Optional<UserPoint> findByUserIdWithLock(String userId);

}
