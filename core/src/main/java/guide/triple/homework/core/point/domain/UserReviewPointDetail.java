package guide.triple.homework.core.point.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;


@Getter
@Setter(value = AccessLevel.PROTECTED)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@DiscriminatorValue("REVIEW")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "userId", "pointGrantType", "eventId" }) })
@Entity
public class UserReviewPointDetail extends UserPointDetail {

    @Enumerated(EnumType.STRING)
    private PointGrantType pointGrantType;

    @Column(nullable = false, updatable = false)
    private String placeId;

    @Column(nullable = false, updatable = false)
    private String reviewId;


    public void changeDeactivate() {
        this.setActive(false);
    }

    public UserReviewPointDetail createRevokeDetail(Long eventId) {

        UserReviewPointDetail revokeDetail = new UserReviewPointDetail();

        UserPointDetail.fillValue(super.createRevokeDetail(eventId), revokeDetail);
        revokeDetail.placeId = this.placeId;
        revokeDetail.reviewId = this.reviewId;
        revokeDetail.pointGrantType = this.pointGrantType;

        return revokeDetail;
    }

    public static UserReviewPointDetail createGrant(String userId, PointGrantType pointGrantType, String placeId, String reviewId, Long eventId) {
        UserReviewPointDetail userReviewPointDetail = new UserReviewPointDetail();
        UserPointDetail.fillValue(UserPointDetail.createGrant(userId, pointGrantType.getPointValue(), eventId), userReviewPointDetail);
        userReviewPointDetail.pointGrantType = pointGrantType;
        userReviewPointDetail.placeId = placeId;
        userReviewPointDetail.reviewId = reviewId;

        return userReviewPointDetail;
    }
}
