package guide.triple.homework.core.orchestration.dto.converter;

import guide.triple.homework.core.orchestration.dto.EventRawSupportDTO;
import guide.triple.homework.core.point.service.dto.EventDTO;

public interface PointEventDtoConverter<T extends EventDTO>{

    EventRawSupportDTO.EVENT_TYPE supportType();

    T convert(EventRawSupportDTO eventRawSupportDTO, Long eventId);

}
