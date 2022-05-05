package io.matefinderbackend.controller;

import io.matefinderbackend.command.CreateAppUserCommand;
import io.matefinderbackend.facade.AppUserFacade;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController("appUser")
public class AppUserController {

    AppUserFacade appUserFacade;

    public AppUserController(AppUserFacade appUserFacade) {
        this.appUserFacade = appUserFacade;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> createUser(@RequestBody CreateAppUserCommand user) {
        Long id = appUserFacade.create(user);
        URI locationUri = URI.create("movies/" + id);
        return ResponseEntity.created(locationUri).build();
    }
}
