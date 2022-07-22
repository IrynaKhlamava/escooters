package com.company.api.service;

import com.company.model.dto.LocationDto;

import java.util.List;

public interface ILocationService {

    LocationDto addLocation(LocationDto location);

    void update(Long id, LocationDto updateData);

    List<LocationDto> getAll(String col);

    LocationDto getById(Long id);

    void delete(Long id);

}
