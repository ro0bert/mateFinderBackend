package io.matefinderbackend.controller;

import io.matefinderbackend.command.CreateEventCommand;
import io.matefinderbackend.dto.EventDto;
import io.matefinderbackend.facade.EventFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("events")
@CrossOrigin(origins = "http://localhost:3000")
public class EventController {

    EventFacade eventFacade;

    public EventController(EventFacade eventService) {
        this.eventFacade = eventService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> createEvent(@RequestBody @Valid CreateEventCommand event) {
        Long id = eventFacade.create(event);
        URI locationUri = URI.create("movies/" + id);
        return ResponseEntity.created(locationUri).build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<EventDto>> getEvents() {
        return ResponseEntity.ok()
                .body(eventFacade.getEvents());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(path = "{eventId}/user/{userId}")
    void addUser(@PathVariable("eventId") long eventId, @PathVariable("userId") long userId) {
        eventFacade.addUser(eventId, userId);
    }
}
