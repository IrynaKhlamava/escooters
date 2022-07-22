package com.company.services;

import com.company.api.dao.ILocationDao;
import com.company.api.dao.IScooterDao;
import com.company.api.service.IScooterService;
import com.company.dao.DaoException;
import com.company.model.Scooter;
import com.company.model.dto.ScooterDto;
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
public class ScooterService implements IScooterService {

    private final IScooterDao scooterDao;

    private final ILocationDao locationDao;

    private final ModelMapper mapper;

    private final MapperUtil mapperUtil;

    @Override
    @Transactional
    public ScooterDto addScooter(ScooterDto scooterDto) {
        try {
            log.info(String.format("addScooter %s,", scooterDto));
            scooterDao.save(mapper.map(scooterDto, Scooter.class));
            return scooterDto;
        } catch (DaoException e) {
            log.warn("addScooter failed", e);
            throw new ServiceException("addScooter failed", e);
        }
    }

    @Override
    @Transactional
    public void update(Long id, ScooterDto updateData) {
        scooterDao.update(id, mapper.map(updateData, Scooter.class));
    }

    @Override
    public List<ScooterDto> getAll(String col) {
        return mapperUtil.convertList(scooterDao.getAll(col), this::convertToDtoWithLocationId);
    }

    @Override
    public ScooterDto getById(Long id) {
        return mapper.map(scooterDao.getById(id), ScooterDto.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        try {
            log.info(String.format("Delete scooter By id: %s ", id));
            scooterDao.delete(scooterDao.getById(id));
        } catch (DaoException e) {
            log.warn(String.format("Delete scooter By id failed: %s ", id));
            throw new ServiceException("Delete scooter By id failed: %s ");
        }
    }

    @Override
    @Transactional
    public void changeLocation(Long idScooter, Long idLocation) {
        try {
            log.info(String.format("Change scooter id: %s  on new location id: %s ", idScooter, idLocation));
            scooterDao.changeLocation(idScooter, locationDao.getById(idLocation));
        } catch (DaoException e) {
            log.warn(String.format("Change scooter location failed: %s ", idScooter));
            throw new ServiceException("Change scooter location failed: %s ");
        }
    }

    @Override
    public List<ScooterDto> getScootersOnLocation(Long id) {
        return mapperUtil.convertList(scooterDao.getScootersOnLocation(id), this::convertToDtoWithLocationId);
    }

    public ScooterDto convertToDtoWithLocationId(Scooter scooter) {
        ScooterDto scooterDto = mapper.map(scooter, ScooterDto.class);
        scooterDto.setLocationId(scooter.getLocation().getId());
        return scooterDto;
    }

}

