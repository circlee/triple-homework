package guide.triple.homework.core.point.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;


@Getter
@ToString
@EqualsAndHashCode
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="domain")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "userId", "pointAction", "eventId" }) })
@Entity
public class UserPointHistory {

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

}
