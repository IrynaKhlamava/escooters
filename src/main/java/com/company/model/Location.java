package com.company.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "locations")
public class Location extends AEntity {

    @Column(name = "number")
    private Integer number;

    @Column(name = "address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(mappedBy = "location")
    @JsonIgnore
    private List<Scooter> scooters;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        if (!super.equals(o)) return false;
        Location location = (Location) o;
        return Objects.equals(number, location.number) &&
                Objects.equals(address, location.address) &&
                Objects.equals(city, location.city) &&
                Objects.equals(scooters, location.scooters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), number, address, city, scooters);
    }

}
