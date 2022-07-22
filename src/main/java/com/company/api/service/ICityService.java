package com.company.api.service;

import com.company.model.dto.CityDto;

import java.util.List;

public interface ICityService {

    CityDto addCity(String name);

    void update(Long id, CityDto updateData);

    List<CityDto> getAll(String col);

    CityDto getById(Long id);

    void delete(Long id);

    CityDto getByName(String city);

}
