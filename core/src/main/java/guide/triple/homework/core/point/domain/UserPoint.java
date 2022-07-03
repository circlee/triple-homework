package guide.triple.homework.core.point.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;


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

}
