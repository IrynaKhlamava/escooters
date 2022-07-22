package com.company.model.dto;

import com.company.model.Scooter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {

    private Long id;

    private Integer number;

    private String address;

    private List<Scooter> scooters;

    private Long cityId;

}
