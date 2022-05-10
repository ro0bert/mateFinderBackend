package io.matefinderbackend.command;

import io.matefinderbackend.dto.AddressDto;
import lombok.Getter;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Getter
public class CreateEventCommand {

    private String name;

    private AddressDto address;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @Min(1)
    private Integer maxUsers;
}
