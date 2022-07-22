package com.company.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "scooters")
public class Scooter extends AEntity {

    @Column(name = "model")
    private String model;

    @Column(name = "date_start_of_use")
    private LocalDateTime dateStartOfUse;

    @Column(name = "max_speed")
    private Integer maxSpeed;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "wheels")
    private Double wheels;

    @Column(name = "max_range")
    private Double maxRange;

    @Column(name = "mileage")
    private Double mileage;

    @Column(name = "charging_time")
    private Double chargingTime;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "location_id")
    @JsonIgnore
    private Location location;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Scooter)) return false;
        if (!super.equals(o)) return false;
        Scooter scooter = (Scooter) o;
        return Objects.equals(model, scooter.model) &&
                Objects.equals(dateStartOfUse, scooter.dateStartOfUse) &&
                Objects.equals(maxSpeed, scooter.maxSpeed) &&
                Objects.equals(weight, scooter.weight) &&
                Objects.equals(wheels, scooter.wheels) &&
                Objects.equals(maxRange, scooter.maxRange) &&
                Objects.equals(mileage, scooter.mileage) &&
                Objects.equals(chargingTime, scooter.chargingTime) &&
                Objects.equals(price, scooter.price) &&
                Objects.equals(location, scooter.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), model, dateStartOfUse, maxSpeed, weight, wheels, maxRange, mileage,
                chargingTime, price, location);
    }

}
