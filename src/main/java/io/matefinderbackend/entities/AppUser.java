package io.matefinderbackend.entities;

import io.matefinderbackend.command.CreateAppUserCommand;
import io.matefinderbackend.dto.AppUserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Objects;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "app_user")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String userName;

    private Integer age;

    private String firstName;

    private String lastName;

    @ManyToMany(mappedBy = "appUsers")
    private Set<Event> events = new LinkedHashSet<>();

    private String email;

    private String phoneNumber;

    public static AppUser fromDto(CreateAppUserCommand dto) {
        return AppUser.builder()
                .userName(dto.getUserName())
                .age(dto.getAge())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .build();
    }

    public static AppUserDto toDto(AppUser entity){
        return AppUserDto.builder()
                .userName(entity.userName)
                .age(entity.age)
                .firstName(entity.firstName)
                .lastName(entity.lastName)
                .email(entity.email)
                .phoneNumber(entity.phoneNumber)
                .build();

    }
}