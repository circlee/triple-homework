package guide.triple.homework.core.point.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPointHistoryRepository extends JpaRepository<UserPointHistory, String> {
}
