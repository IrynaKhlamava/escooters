package com.company.api.service;

import com.company.model.dto.ScooterDto;

import java.util.List;

public interface IScooterService {

    ScooterDto addScooter(ScooterDto scooter);

    void update(Long id, ScooterDto updateData);

    List<ScooterDto> getAll(String col);

    ScooterDto getById(Long id);

    void delete(Long id);

    void changeLocation(Long idScooter, Long idLocation);

    List<ScooterDto> getScootersOnLocation(Long id);

}
