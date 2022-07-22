package com.company.api.dao;

import com.company.model.City;

import java.util.List;

public interface ICityDao {

    City save(City city);

    void update(Long id, City updateData);

    List<City> getAll(String col);

    City getById(Long id);

    void delete(City city);

    City getByName(String city);

}
