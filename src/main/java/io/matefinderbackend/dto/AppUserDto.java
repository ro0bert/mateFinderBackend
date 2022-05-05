package io.matefinderbackend.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class AppUserDto implements Serializable {
    private final Long id;
    private final String userName;
    private final Integer age;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phoneNumber;
}
