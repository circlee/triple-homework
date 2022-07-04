package guide.triple.homework.core.orchestration;

import guide.triple.homework.core.event.service.EventService;
import guide.triple.homework.core.orchestration.dto.EventRawSupportDTO;
import guide.triple.homework.core.orchestration.dto.converter.PointEventDtoConverters;
import guide.triple.homework.core.point.service.UserPointOverallService;
import guide.triple.homework.core.point.service.dto.EventDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EventPointOrchestrationService {

    private final EventService eventService;
    private final PointEventDtoConverters pointEventDtoConverters;
    private final UserPointOverallService userPointOverallService;


    public void handleEvent(EventRawSupportDTO eventRawSupportDTO) {

        Long eventId = eventService.saveEvent(eventRawSupportDTO.getRawJson());

        EventDTO eventDTO = pointEventDtoConverters.convert(eventRawSupportDTO, eventId);

        userPointOverallService.addUserPointByEvent(eventDTO);

    }

}
