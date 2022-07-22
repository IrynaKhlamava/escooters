package com.company.dao;

import com.company.api.dao.GenericDao;

import com.company.model.AEntity;

import lombok.extern.log4j.Log4j2;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Log4j2
public abstract class AbstractDao <T extends AEntity> implements GenericDao<T> {

    @PersistenceContext()
    protected EntityManager entityManager;

    public AbstractDao() {
    }

    @Override
    public T save(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public T getById(Long id) {
        return entityManager.find(getClazz(), id);
    }

    protected abstract Class<T> getClazz();

    @Override
    public void delete(T entity) {
        try {
            entityManager.remove(entity);
        } catch (Exception e) {
            log.warn("DELETE failed ", e);
            throw new DaoException("DELETE failed");
        }
    }

    @Override
    public List<T> getAll(String col) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(getClazz());
        Root<T> root = query.from(getClazz());
        if (!col.equals("")) {
            query.orderBy(builder.asc(root.get(col)));
        }
        return entityManager.createQuery(query).getResultList();
    }

}
