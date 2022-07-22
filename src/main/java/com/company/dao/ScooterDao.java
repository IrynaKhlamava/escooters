package com.company.dao;

import com.company.api.dao.IScooterDao;
import com.company.model.Location;
import com.company.model.Scooter;
import com.company.model.Scooter_;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Log4j2
@Repository
public class ScooterDao extends AbstractDao<Scooter> implements IScooterDao {

    @Override
    protected Class<Scooter> getClazz() {
        return Scooter.class;
    }

    @Override
    public void update(Long id, Scooter update) {
        Scooter scooter = getById(id);
        Optional.ofNullable(update.getModel()).ifPresent(scooter::setModel);
        Optional.ofNullable(update.getDateStartOfUse()).ifPresent(scooter::setDateStartOfUse);
        Optional.ofNullable(update.getMaxSpeed()).ifPresent(scooter::setMaxSpeed);
        Optional.ofNullable(update.getWeight()).ifPresent(scooter::setWeight);
        Optional.ofNullable(update.getWheels()).ifPresent(scooter::setWheels);
        Optional.ofNullable(update.getMaxRange()).ifPresent(scooter::setMaxRange);
        Optional.ofNullable(update.getMileage()).ifPresent(scooter::setMileage);
        Optional.ofNullable(update.getChargingTime()).ifPresent(scooter::setChargingTime);
        Optional.ofNullable(update.getPrice()).ifPresent(scooter::setPrice);
        Optional.ofNullable(update.getLocation()).ifPresent(scooter::setLocation);
        entityManager.merge(scooter);
    }

    @Override
    public void changeLocation(Long idScooter, Location location) {
        Scooter scooter = getById(idScooter);
        scooter.setLocation(location);
        entityManager.merge(scooter);
    }

    @Override
    public List<Scooter> getScootersOnLocation(Long id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Scooter> query = builder.createQuery(Scooter.class);
        Root<Scooter> root = query.from(Scooter.class);
        query.select(root).where(builder.equal(root.get(Scooter_.LOCATION), id));
        return entityManager.createQuery(query).getResultList();
    }

}
