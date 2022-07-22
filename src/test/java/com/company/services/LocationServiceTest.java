package com.company.services;

import com.company.api.dao.ILocationDao;
import com.company.api.service.ILocationService;
import com.company.model.Location;
import com.company.model.dto.LocationDto;
import com.company.util.MapperUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LocationServiceTest {

    private ILocationDao locationDao;
    private ModelMapper mapper;
    private MapperUtil mapperUtil;
    private ILocationService locationService;

    @BeforeEach
    public void init() {
        locationDao = Mockito.mock(ILocationDao.class);
        mapperUtil = Mockito.mock(MapperUtil.class);
        mapper = Mockito.mock(ModelMapper.class);
        locationService = new LocationService(locationDao, mapper, mapperUtil);
    }

    @Test
    void addLocation() {
        LocationDto locationDto = new LocationDto();
        Location location = new Location();
        when(mapper.map(locationDto, Location.class)).thenReturn(location);
        locationService.addLocation(locationDto);
        verify(locationDao, times(1)).save(location);
        verify(mapper, times(1)).map(locationDto, Location.class);
    }

    @Test
    void update() {
        Location location = new Location();
        LocationDto locationDto = new LocationDto();
        when(mapper.map(locationDto, Location.class)).thenReturn(location);
        locationService.update(1L, locationDto);
        verify(locationDao, times(1)).update(1L, location);
    }

    @Test
    void getAll() {
        List<Location> locations = new ArrayList<>();
        List<LocationDto> locationDtoList = new ArrayList<>();
        when(locationDao.getAll("")).thenReturn(locations);
        when(mapperUtil.convertList(locations, LocationDto.class)).thenReturn(locationDtoList);
        assertEquals(locationDtoList, locationService.getAll(""));
        verify(locationDao, times(1)).getAll("");
    }

    @Test
    void delete() {
        Location location = new Location();
        when(locationDao.getById(anyLong())).thenReturn(location);
        locationDao.delete(location);
        verify(locationDao, times(1)).delete(location);

    }

}