package io.matefinderbackend.entities;

import io.matefinderbackend.command.CreateEventCommand;
import io.matefinderbackend.dto.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import static java.util.Objects.isNull;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "number")
    private Integer number;


    public static Address fromDto(AddressDto dto){
        if(isNull(dto) || dto.isEmpty()){
            return null;
        }

        return builder()
                .city(dto.getCity())
                .street(dto.getStreet())
                .number(dto.getNumber())
                .build();
    }

    public static AddressDto toDto(Address address) {
        return AddressDto.builder()
                .id(address.id)
                .city(address.city)
                .street(address.street)
                .number(address.number)
                .build();
    }
}