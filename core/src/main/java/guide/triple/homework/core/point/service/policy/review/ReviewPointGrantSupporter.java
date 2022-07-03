package guide.triple.homework.core.point.service.policy.review;

import guide.triple.homework.core.point.domain.PointGrantType;
import guide.triple.homework.core.point.service.dto.ReviewEventDTO;
import guide.triple.homework.core.point.service.policy.PointGrantPolicySupporter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ReviewPointGrantSupporter implements PointGrantPolicySupporter<ReviewEventDTO> {

    private final List<ReviewPointGrantPolicy> policies;


    @Override
    public Class<ReviewEventDTO> getSupportEventDTOClass() {
        return ReviewEventDTO.class;
    }

    @Override
    public Set<PointGrantType> getPointGrantTypes(ReviewEventDTO eventDTO) {
        return policies.stream()
                .map(policy -> policy.getPointGrantType(eventDTO))
                .map(grantType -> grantType.orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }
}
