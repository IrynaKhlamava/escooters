package com.company.api.dao;

import com.company.model.Location;

import java.util.List;

public interface ILocationDao {

    Location save(Location location);

    void update(Long id, Location updateData);

    List<Location> getAll(String col);

    Location getById(Long id);

    void delete(Location location);

}
