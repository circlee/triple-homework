package guide.triple.homework.core.point.domain;

import guide.triple.homework.core.point.exception.InvalidActivePointException;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;


@Slf4j
@Getter
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
public class UserPoint {

    @Id
    private String userId;

    @Column(precision = 19, scale = 0, nullable = false)
    private BigDecimal activePoint;

    public static UserPoint create(String userId) {
        return UserPoint.builder()
                .userId(userId)
                .activePoint(BigDecimal.ZERO)
                .build();
    }

    public void updateActivePointWithGrantedValue(BigDecimal granted) {
        this.activePoint = this.activePoint.add(granted);
    }

    private void validActivePoint(BigDecimal activePoint) {
        if(BigDecimal.ZERO.compareTo(activePoint) > 1) {
            throw new InvalidActivePointException("invalid active point : " + activePoint);
        }
    }

}
