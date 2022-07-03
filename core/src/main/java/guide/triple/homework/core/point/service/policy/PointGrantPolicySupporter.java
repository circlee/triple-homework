package guide.triple.homework.core.point.service.policy;

import guide.triple.homework.core.point.domain.PointGrantType;
import guide.triple.homework.core.point.service.dto.EventDTO;

import java.util.Set;

public interface PointGrantPolicySupporter<T extends EventDTO> {

    Class<T> getSupportEventDTOClass();

    Set<PointGrantType> getPointGrantTypes(T eventDTO);

}
