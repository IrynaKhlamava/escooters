package com.company.api.dao;

import com.company.model.Location;
import com.company.model.Scooter;

import java.util.List;

public interface IScooterDao {

    Scooter save(Scooter scooter);

    void update(Long id, Scooter updateData);

    List<Scooter> getAll(String col);

    Scooter getById(Long id);

    void delete(Scooter scooter);

    void changeLocation(Long idScooter, Location location);

    List<Scooter> getScootersOnLocation(Long id);

}
