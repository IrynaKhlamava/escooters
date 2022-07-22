package com.company.api.service;

import com.company.model.Order;
import com.company.model.dto.OrderDto;

import java.util.List;

public interface IOrderService {

    OrderDto addOrder(OrderDto order) ;

    void update(Long id, OrderDto updateData);

    List<OrderDto> getAll(String col);

    OrderDto getById(Long id);

    void delete(Long id);

    List<OrderDto> getHistory(Long id, String col);

    OrderDto closeOrder(Long id, Double mileage, Long newLocationId);

    double closeUserRental(Order order, Double scooterPrice);

    Double closeScooterRental(Order order, Double mileage, Long newLocationId);

}
