package guide.triple.homework.core.point.service;

import guide.triple.homework.core.point.domain.PointGrantType;
import guide.triple.homework.core.point.service.dto.EventDTO;

import java.math.BigDecimal;
import java.util.Set;


interface UserPointTypeGrantService<T extends EventDTO> {

    EventDTO.EVENT_TYPE supportType();

    BigDecimal userPointGrant(T eventDto, Set<PointGrantType> tobePointGrantTypeSet);

}
