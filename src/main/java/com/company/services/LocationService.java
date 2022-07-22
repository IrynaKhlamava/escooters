package com.company.services;

import com.company.api.dao.ILocationDao;

import com.company.api.service.ILocationService;

import com.company.dao.DaoException;
import com.company.model.Location;

import com.company.model.dto.LocationDto;

import com.company.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Log4j2
@Service
public class LocationService implements ILocationService {

    private final ILocationDao locationDao;

    private final ModelMapper mapper;

    private final MapperUtil mapperUtil;

    @Override
    @Transactional
    public LocationDto addLocation(LocationDto locationDto) {
        try {
            if (locationDto.getId() != null) {
                log.warn(String.format("Location already exists. AddLocation failed"));
                throw new ServiceException("Location already exists. AddLocation failed");
            }
            log.info(String.format("addLocation %s", locationDto));
            locationDao.save(mapper.map(locationDto, Location.class));
            return locationDto;
        } catch (DaoException e) {
            log.warn("addLocation failed", e);
            throw new ServiceException("addLocation failed", e);
        }
    }

    @Override
    @Transactional
    public void update(Long id, LocationDto updateData) {
        locationDao.update(id, mapper.map(updateData, Location.class));
    }

    @Override
    public List<LocationDto> getAll(String col) {
        return mapperUtil.convertList(locationDao.getAll(col), this::convertToDtoWithCityId);
    }

    @Override
    public LocationDto getById(Long id) {
        return (mapper.map(locationDao.getById(id), LocationDto.class));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        try {
            log.info(String.format("Delete Location By id: %s ", id));
            locationDao.delete(locationDao.getById(id));
        } catch (DaoException e) {
            log.warn(String.format("Delete Location By id failed: %s ", id));
            throw new ServiceException("Delete Location By id failed: %s ");
        }
    }

    public LocationDto convertToDtoWithCityId(Location location) {
        LocationDto locationDto = mapper.map(location, LocationDto.class);
        locationDto.setCityId(location.getCity().getId());
        return locationDto;
    }

}
