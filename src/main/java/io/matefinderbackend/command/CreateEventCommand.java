package io.matefinderbackend.command;

import io.matefinderbackend.dto.AddressDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateEventCommand {

    private String name;

    private AddressDto address;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Integer maxUsers;
}
