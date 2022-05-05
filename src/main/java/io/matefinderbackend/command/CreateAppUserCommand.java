package io.matefinderbackend.command;

import lombok.Getter;

@Getter
public class CreateAppUserCommand {

    private String userName;

    private Integer age;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;
}
