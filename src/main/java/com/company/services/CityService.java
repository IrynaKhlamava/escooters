package com.company.services;

import com.company.api.dao.ICityDao;
import com.company.api.service.ICityService;
import com.company.dao.DaoException;
import com.company.model.City;
import com.company.model.dto.CityDto;
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

public class CityService implements ICityService {

    private final ICityDao cityDao;

    private final ModelMapper mapper;

    private final MapperUtil mapperUtil;

    @Override
    @Transactional
    public CityDto addCity(String name) {
        try {
            log.info(String.format("addCity name: %s", name));
            City city = new City(name);
            cityDao.save(city);
            return mapper.map(city, CityDto.class);
        } catch (DaoException e) {
            log.warn("addCity failed", e);
            throw new ServiceException("addCity failed", e);
        }
    }

    @Override
    @Transactional
    public void update(Long id, CityDto updateData) {
        cityDao.update(id, mapper.map(updateData, City.class));
    }

    @Override
    public List<CityDto> getAll(String col) {
        return mapperUtil.convertList(cityDao.getAll(col), CityDto.class);
    }

    @Override
    public CityDto getById(Long id) {
        return mapper.map(cityDao.getById(id), CityDto.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        City city = cityDao.getById(id);
        if (city != null) {
            cityDao.delete(city);
        }
    }

    @Override
    public CityDto getByName(String city) {
        return mapper.map(cityDao.getByName(city), CityDto.class);
    }

}
