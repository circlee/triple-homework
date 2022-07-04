package guide.triple.homework.core.event.service;

import guide.triple.homework.core.event.domain.Event;
import guide.triple.homework.core.event.domain.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Component
public class EventService {

    private final EventRepository eventRepository;

    @Transactional
    public Long saveEvent(String rawJson) {
        Event event = eventRepository.save(Event.create(rawJson));
        return event.getId();
    }

}
