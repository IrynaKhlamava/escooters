package com.company.dao;

import com.company.api.dao.ICityDao;
import com.company.model.City;
import com.company.model.City_;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Log4j2
@Repository
public class CityDao extends AbstractDao<City> implements ICityDao {

    @Override
    public void update(Long id, City update) {
        City city = getById(id);
        Optional.ofNullable(update.getName()).ifPresent(city::setName);
    }

    @Override
    public City getByName(String city) {
        try {
                CriteriaBuilder builder = entityManager.getCriteriaBuilder();
                CriteriaQuery<City> query = builder.createQuery(City.class);
                Root<City> root = query.from(City.class);
                query.select(root).where(builder.equal(root.get(City_.NAME), city));
                return entityManager.createQuery(query).getSingleResult();
            } catch (Exception e) {
                log.warn("Get city by name failed");
                throw new DaoException(String.format("Get city by name failed"));
            }
        }

    @Override
    protected Class<City> getClazz() {
        return City.class;
    }

}
