package guide.triple.homework.core.orchestration.dto.converter;

import guide.triple.homework.core.orchestration.dto.EventRawSupportDTO;
import guide.triple.homework.core.orchestration.exception.NotFoundSupporterException;
import guide.triple.homework.core.point.service.dto.EventDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class PointEventDtoConverters{

    private final Map<EventRawSupportDTO.EVENT_TYPE, PointEventDtoConverter> pointEventDtoConverterMap;

    public PointEventDtoConverters(List<PointEventDtoConverter> converters) {
        this.pointEventDtoConverterMap = converters.stream()
                .collect(Collectors.toMap(PointEventDtoConverter::supportType, Function.identity(), (o1, o2) -> o1));
    }

    public EventDTO convert(EventRawSupportDTO eventRawSupportDTO, Long eventId) {
        return Optional.ofNullable(pointEventDtoConverterMap.get(eventRawSupportDTO.getType()))
                .map(converter -> converter.convert(eventRawSupportDTO, eventId))
                .orElseThrow(() -> new NotFoundSupporterException("not found converter"));

    }

}
