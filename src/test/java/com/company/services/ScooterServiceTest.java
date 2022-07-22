package com.company.services;

import com.company.api.dao.ILocationDao;
import com.company.api.dao.IScooterDao;
import com.company.api.service.IScooterService;
import com.company.model.Location;
import com.company.model.Scooter;
import com.company.model.dto.ScooterDto;
import com.company.util.MapperUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ScooterServiceTest {

    private IScooterDao scooterDao;
    private ILocationDao locationDao;
    private ModelMapper mapper;
    private MapperUtil mapperUtil;
    private IScooterService scooterService;

    @BeforeEach
    public void init() {
        scooterDao = Mockito.mock(IScooterDao.class);
        locationDao = Mockito.mock(ILocationDao.class);
        mapper = Mockito.mock(ModelMapper.class);
        mapperUtil = Mockito.mock(MapperUtil.class);
        scooterService = new ScooterService(scooterDao, locationDao, mapper, mapperUtil);
    }

    @Test
    void addScooter() {
        Scooter scooter = new Scooter();
        ScooterDto scooterDto = new ScooterDto();
        when(mapper.map(scooterDto, Scooter.class)).thenReturn(scooter);
        scooterService.addScooter(scooterDto);
        verify(scooterDao, times(1)).save(scooter);
    }

    @Test
    void update() {
        Scooter scooter = new Scooter();
        ScooterDto scooterDto = new ScooterDto();
        when(mapper.map(scooterDto, Scooter.class)).thenReturn(scooter);
        scooterService.update(1L, scooterDto);
        verify(scooterDao, times(1)).update(1L, scooter);
    }

    @Test
    void getAll() {
        List<Scooter> scooters = new ArrayList<>();
        List<ScooterDto> scooterDtoList = new ArrayList<>();
        when(scooterDao.getAll("")).thenReturn(scooters);
        when(mapperUtil.convertList(scooters, ScooterDto.class)).thenReturn(scooterDtoList);
        assertEquals(scooterDtoList, scooterService.getAll(""));
        verify(scooterDao, times(1)).getAll("");
    }

    @Test
    void delete() {
        Scooter scooter = new Scooter();
        when(scooterDao.getById(anyLong())).thenReturn(scooter);
        scooterDao.delete(scooter);
        verify(scooterDao, times(1)).delete(scooter);
    }

    @Test
    void changeLocation() {
        Location location = new Location();
        location.setId(1L);
        when(locationDao.getById(anyLong())).thenReturn(location);
        scooterService.changeLocation(1L, 2L);
        verify(scooterDao, times(1)).changeLocation(1L, location);
    }

    @Test
    void getScootersOnLocation() {
        List<Scooter> scooters = new ArrayList<>();
        List<ScooterDto> scooterDtoList = new ArrayList<>();
        when(scooterDao.getScootersOnLocation(anyLong())).thenReturn(scooters);
        when(mapperUtil.convertList(scooters, ScooterDto.class)).thenReturn(scooterDtoList);
        assertEquals(scooterDtoList, scooterService.getScootersOnLocation(1L));
        verify(scooterDao, times(1)).getScootersOnLocation(1L);
    }

}