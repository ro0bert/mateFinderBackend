package io.matefinderbackend.facade;

import io.matefinderbackend.command.CreateEventCommand;
import io.matefinderbackend.dto.EventDto;
import io.matefinderbackend.entities.AppUser;
import io.matefinderbackend.entities.Event;
import io.matefinderbackend.exception.EntityNotFoundException;
import io.matefinderbackend.repository.AppUserRepository;
import io.matefinderbackend.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventFacade {

    EventRepository eventRepository;
    AppUserRepository appUserRepository;

    public EventFacade(EventRepository eventRepository, AppUserRepository appUserRepository) {
        this.eventRepository = eventRepository;
        this.appUserRepository = appUserRepository;
    }

    public Long create(CreateEventCommand dto) {
        Event event = Event.fromDto(dto);
        Event savedEvent = eventRepository.save(event);
        return savedEvent.getId();
    }

    public List<EventDto> getEvents() {
        return eventRepository.getAllBy().stream()
                .map(Event::toDto)
                .collect(Collectors.toList());
    }

    public void addUser(long eventId, long userId) {
        //TODO return value to test it
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event with id: " + eventId + " not found"));
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("App User with id: " + userId + " not found"));

        event.validateIfShouldAdd(user);
        event.addUser(user);
        eventRepository.saveAndFlush(event);

    }
}
