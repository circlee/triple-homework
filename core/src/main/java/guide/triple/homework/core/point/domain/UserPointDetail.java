package guide.triple.homework.core.point.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;


@Getter
@Setter(value = AccessLevel.PROTECTED)
@ToString
@EqualsAndHashCode
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="domain")
@Entity
public class UserPointDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private PointAction pointAction;

    @Column(precision = 19, scale = 0, nullable = false, updatable = false)
    private BigDecimal pointValue;

    @Column(nullable = false, updatable = false)
    private Long eventId;

    @Column(nullable = false)
    private Boolean active;

    @Column(updatable = false)
    private Long refEventId;

    public UserPointDetail createRevokeDetail(Long eventId) {

        return UserPointDetail.builder()
                .userId(this.userId)
                .pointValue(this.pointValue)
                .pointAction(PointAction.REVOKE)
                .active(false)
                .eventId(eventId)
                .refEventId(this.eventId)
                .build();

    }

    protected static UserPointDetail createGrant(String userId, BigDecimal pointValue, Long eventId) {
        return UserPointDetail.builder()
                .userId(userId)
                .pointAction(PointAction.GRANT)
                .pointValue(pointValue)
                .eventId(eventId)
                .active(true)
                .build();
    }

    protected static <T extends UserPointDetail> void fillValue(UserPointDetail parent, T child) {

        child.setId(parent.getId());
        child.setUserId(parent.getUserId());
        child.setPointAction(parent.getPointAction());
        child.setPointValue(parent.getPointValue());
        child.setEventId(parent.getEventId());
        child.setActive(parent.getActive());
        child.setRefEventId(parent.getRefEventId());
    }


}
