package com.company.api.dao;

import com.company.model.Order;

import java.util.List;

public interface IOrderDao {

    Order save(Order order);

    void update(Long id, Order updateData);

    List<Order> getAll(String col);

    Order getById(Long id);

    void delete(Order order);

    List<Order> getHistory(Long id, String col);

}
