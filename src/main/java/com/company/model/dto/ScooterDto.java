package com.company.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScooterDto {

    private Long id;

    private String model;

    private LocalDateTime dateStartOfUse;

    private Integer maxSpeed;

    private Double weight;

    private Double wheels;

    private Double maxRange;

    private Double mileage;

    private Double chargingTime;

    private Double price;

    private Long locationId;

}
