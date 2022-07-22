package com.company.services;

import com.company.api.dao.ICityDao;
import com.company.api.service.ICityService;
import com.company.model.City;
import com.company.model.dto.CityDto;

import com.company.util.MapperUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CityServiceTest {

    private ICityDao cityDao;
    private ICityService cityService;
    private ModelMapper mapper;
    private MapperUtil mapperUtil;

    @BeforeEach
    public void init() {
        cityDao = Mockito.mock(ICityDao.class);
        mapperUtil = Mockito.mock(MapperUtil.class);
        mapper = Mockito.mock(ModelMapper.class);
        cityService = new CityService(cityDao, mapper, mapperUtil);
    }

    @Test
    void getAll() {
        List<City> cities = new ArrayList<>();
        List<CityDto> cityDtoList = new ArrayList<>();
        when(cityDao.getAll("")).thenReturn(cities);
        when(mapperUtil.convertList(cities, CityDto.class)).thenReturn(cityDtoList);
        assertEquals(cityDtoList, cityService.getAll(""));
        verify(cityDao, times(1)).getAll("");
    }

    @Test
    void addCity() {
        City city = new City("name");
        cityDao.save(city);
        verify(cityDao, times(1)).save(city);
    }

}
