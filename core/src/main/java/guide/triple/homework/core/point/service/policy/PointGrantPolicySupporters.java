package guide.triple.homework.core.point.service.policy;

import guide.triple.homework.core.point.domain.PointGrantType;
import guide.triple.homework.core.point.service.dto.EventDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
public class PointGrantPolicySupporters {

    private final Map<Class<? extends EventDTO>, PointGrantPolicySupporter> supporterMap;


    public PointGrantPolicySupporters(List<PointGrantPolicySupporter> supporters) {
        this.supporterMap = supporters.stream()
                .collect(Collectors.toMap(PointGrantPolicySupporter::getSupportEventDTOClass, Function.identity(), (o1, o2) -> o1));
    }

    public Set<PointGrantType> getPointGrantTypes(EventDTO eventDTO) {
        return Optional.ofNullable(supporterMap.get(getSupportClass(eventDTO)))
                .map(supporter -> supporter.getPointGrantTypes(eventDTO))
                .orElse(Collections.emptySet());
    }

    private Class<? extends EventDTO> getSupportClass(EventDTO eventDTO) {
        return supporterMap.keySet().stream()
                .filter(supportClass -> supportClass.isInstance(eventDTO))
                .findFirst()
                .orElse(null);
    }
}
