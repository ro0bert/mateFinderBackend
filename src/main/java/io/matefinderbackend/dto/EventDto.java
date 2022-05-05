package io.matefinderbackend.dto;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EventDto {

    private Long id;

    private String name;

    private AddressDto addressDto;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Integer maxUsers;

    private List<AppUserDto> appUsersDto;


}
