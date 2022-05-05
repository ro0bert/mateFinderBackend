package io.matefinderbackend.facade;

import io.matefinderbackend.command.CreateAppUserCommand;
import io.matefinderbackend.entities.AppUser;
import io.matefinderbackend.repository.AppUserRepository;
import org.springframework.stereotype.Service;

@Service
public class AppUserFacade {

    AppUserRepository appUserRepository;

    public AppUserFacade(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public Long create(CreateAppUserCommand createAppUserCommand) {

        AppUser user = AppUser.fromDto(createAppUserCommand);
        AppUser savedUser = appUserRepository.save(user);
        return savedUser.getId();
    }
}
