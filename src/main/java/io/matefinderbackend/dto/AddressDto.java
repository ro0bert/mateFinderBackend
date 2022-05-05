package io.matefinderbackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static java.util.Objects.isNull;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private Long id;

    private String city;

    private String street;

    private Integer number;

    @JsonIgnore
    public boolean isEmpty() {
        return isNull(street)
                && isNull(number)
                && isNull(city);
    }
}
