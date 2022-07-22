package com.company.dao;

import com.company.api.dao.ILocationDao;
import com.company.model.Location;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Log4j2
@Repository
public class LocationDao extends AbstractDao<Location> implements ILocationDao {

    @Override
    protected Class<Location> getClazz() {
        return Location.class;
    }

    @Override
    public void update(Long id, Location update) {
        Location location = getById(id);
        Optional.ofNullable(update.getNumber()).ifPresent(location::setNumber);
        Optional.ofNullable(update.getAddress()).ifPresent(location::setAddress);
        Optional.ofNullable(update.getCity()).ifPresent(location::setCity);
        entityManager.merge(location);
    }

}
