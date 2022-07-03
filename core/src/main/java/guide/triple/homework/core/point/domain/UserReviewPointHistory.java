package guide.triple.homework.core.point.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;


@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@DiscriminatorValue("REVIEW")
@Entity
public class UserReviewPointHistory extends UserPointHistory {

    @Enumerated(EnumType.STRING)
    private PointGrantType pointGrantType;

    @Column(nullable = false, updatable = false)
    private String placeId;

    @Column(nullable = false, updatable = false)
    private String reviewId;


}
