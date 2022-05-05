package io.matefinderbackend.entities;

import static com.google.common.base.Preconditions.checkArgument;
import io.matefinderbackend.command.CreateEventCommand;
import io.matefinderbackend.dto.EventDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "event")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "address_id")
    private Address address;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(name = "event_app_users",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "app_users_id"))
    private Set<AppUser> appUsers = new LinkedHashSet<>();

    private Integer maxUsers;

    public static Event fromDto(CreateEventCommand dto){
        return builder()
                .name(dto.getName())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .maxUsers(dto.getMaxUsers())
                .address(Address.fromDto(dto.getAddress()))
                .build();
    }

    public static EventDto toDto(Event event){
        return EventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .maxUsers(event.getMaxUsers())
                .addressDto(Address.toDto(event.getAddress()))
                .appUsersDto(event.getAppUsers().stream().map(AppUser::toDto).collect(Collectors.toList()))
                .build();
    }

    public void validateIfShouldAdd(AppUser userToAdd) {


        //TODO thew customized exception
        //TODO return value to test it
        if(appUsers.size() >= maxUsers){
            throw new IllegalArgumentException("Event has max users!");
        }
        if (isAlreadyAdded(userToAdd)){
            throw new IllegalArgumentException("User already added for that event");
        }
    }

    private boolean isAlreadyAdded(AppUser userToAdd) {
        return appUsers.stream().parallel().anyMatch(u -> u.getId().equals(userToAdd.getId()));
    }

    public Event addUser(AppUser user){
        this.getAppUsers().add(user);
        user.getEvents().add(this);
        return this;
    }


}