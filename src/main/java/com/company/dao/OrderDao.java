package com.company.dao;

import com.company.api.dao.IOrderDao;
import com.company.model.*;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Log4j2
@Repository
public class OrderDao extends AbstractDao<Order> implements IOrderDao {

    @Override
    protected Class<Order> getClazz() {
        return Order.class;
    }

    @Override
    public void update(Long id, Order update) {
        Order order = getById(id);
        if (order != null && order.getSum() == null) {
            Optional.ofNullable(update.getNumber()).ifPresent(order::setNumber);
            Optional.ofNullable(update.getRate()).ifPresent(order::setRate);
            Optional.ofNullable(update.getScooterId()).ifPresent(order::setScooterId);
            entityManager.merge(order);
        } else {
            log.warn(String.format("Order is already closed or doesn't exist. Update failed. OrderId=%s", id));
            throw new DaoException("Order is already closed or doesn't exist. Update failed");
        }
    }

    @Override
    public List<Order> getHistory(Long id, String col) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> query = builder.createQuery(Order.class);
        Root<Order> root = query.from(Order.class);
        query.select(root).where(builder.equal(root.get(col), id));
        return entityManager.createQuery(query).getResultList();
    }

}
