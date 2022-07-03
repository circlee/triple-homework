package guide.triple.homework.core.point.service;

import guide.triple.homework.core.point.domain.PointGrantType;
import guide.triple.homework.core.point.exception.NoSupportEventTypeHandlerException;
import guide.triple.homework.core.point.service.dto.EventDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;


@Component
class UserPointTypeGrantServiceSupporter {

    private final Map<EventDTO.EVENT_TYPE, UserPointTypeGrantService> userPointTypeGrantServiceMap;

    public UserPointTypeGrantServiceSupporter(List<UserPointTypeGrantService> userPointTypeGrantServices) {
        this.userPointTypeGrantServiceMap = userPointTypeGrantServices.stream()
                .collect(Collectors.toMap(UserPointTypeGrantService::supportType, Function.identity(), (o1, o2) -> o1));
    }

    BigDecimal userPointGrant(EventDTO eventDTO, Set<PointGrantType> tobePointGrantTypeSet) {
        return Optional.ofNullable(userPointTypeGrantServiceMap.get(eventDTO.getType()))
                .map(grantService -> grantService.userPointGrant(eventDTO, tobePointGrantTypeSet))
                .orElseThrow(() -> new NoSupportEventTypeHandlerException("no support event Type " + eventDTO.getType()));
    }
}
