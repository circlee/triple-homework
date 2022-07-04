package guide.triple.homework.app.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guide.triple.homework.app.event.model.Result;
import guide.triple.homework.core.orchestration.EventPointOrchestrationService;
import guide.triple.homework.core.orchestration.dto.EventRawSupportDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/events")
public class EventController {

    private static final ObjectMapper om = new ObjectMapper();
    private final EventPointOrchestrationService eventPointOrchestrationService;

    @PostMapping
    public Result addEvents(@RequestBody String raw) {

        EventRawSupportDTO eventRawSupportDTO = getEventRawSupportDTO(raw);

        eventPointOrchestrationService.handleEvent(eventRawSupportDTO);

        return Result.SUCCESS;
    }

    private EventRawSupportDTO getEventRawSupportDTO(String raw) {
        EventRawSupportDTO eventRawSupportDTO = convert(raw, EventRawSupportDTO.class);
        eventRawSupportDTO.setRawJson(raw);
        return eventRawSupportDTO;
    }

    private <T> T convert(String rawJson, Class<T> clazz) {
        try {
            return (T) om.readValue(rawJson, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
