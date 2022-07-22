package com.company.services;

import com.company.api.dao.ILocationDao;
import com.company.api.dao.IOrderDao;
import com.company.api.dao.IScooterDao;
import com.company.api.dao.IUserDao;
import com.company.api.service.IOrderService;
import com.company.dao.DaoException;
import com.company.model.*;

import com.company.model.dto.OrderDto;
import com.company.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@RequiredArgsConstructor
@Log4j2
@Service
public class OrderService implements IOrderService {

    private final IOrderDao orderDao;

    private final IScooterDao scooterDao;

    private final IUserDao userDao;

    private final ILocationDao locationDao;

    private final ModelMapper mapper;

    private final MapperUtil mapperUtil;

    @Override
    @Transactional
    public OrderDto addOrder(OrderDto orderDto) {
        try {
            log.info(String.format("addOrder order: %s", orderDto));
            Order order = mapper.map(orderDto, Order.class);
            order.setStartDate(LocalDateTime.now());
            order.setFinishDate(order.getStartDate().plusHours(1));
            if (order.getRate().toString().equals(Rate.SUBSCRIPTION.name())) {
                AppUser user = userDao.getById(order.getUserId());
                if (user.getSubHours() < 0) {
                    log.warn("The subscription is over. AddOrder failed");
                    throw new ServiceException("The subscription is over. AddOrder failed");
                }
            }
            orderDao.save(order);
            return mapper.map(order, OrderDto.class);
        } catch (DaoException e) {
            log.warn("addOrder failed", e);
            throw new ServiceException("addOrder failed", e);
        }
    }

    @Override
    @Transactional
    public void update(Long id, OrderDto orderDto) {
        try {
            orderDao.update(id, mapper.map(orderDto, Order.class));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<OrderDto> getAll(String col) {
        return mapperUtil.convertList(orderDao.getAll(col), OrderDto.class);
    }

    @Override
    public OrderDto getById(Long id) {
        return mapper.map(orderDao.getById(id), OrderDto.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Order order = orderDao.getById(id);
        if (order != null) {
            orderDao.delete(order);
        }
    }

    @Override
    public List<OrderDto> getHistory(Long id, String col) {
        return mapperUtil.convertList(orderDao.getHistory(id, col), OrderDto.class);
    }

    @Override
    @Transactional
    public OrderDto closeOrder(Long id, Double mileage, Long newLocationId) {
        Order order = orderDao.getById(id);
        if (order.getSum() == null) {
            Double sum = closeUserRental(order, closeScooterRental(order, mileage, newLocationId));
            order.setSum(sum);
            order.setFinishDate(LocalDateTime.now());
            orderDao.save(order);
        } else {
            log.warn(String.format("Order already closed %s ", id));
            throw new ServiceException("Order already closed");
        }
        return mapper.map(order, OrderDto.class);
    }

    @Override
    @Transactional
    public double closeUserRental(Order order, Double scooterPrice) {
        double sum;
        Rate rate = order.getRate();
        long userId = order.getUserId();
        AppUser user = userDao.getById(userId);
        LocalDateTime sd = order.getStartDate();
        LocalDateTime nd = LocalDateTime.now();
        Integer hr = nd.getHour() - sd.getHour();
        Period time = Period.between(sd.toLocalDate(), nd.toLocalDate());
        hr = hr + time.getDays() * 24;
        if (hr == 0) {
            hr = 1;
        }
        user.setHours(user.getHours() + hr);
        sum = scooterPrice * hr - ((scooterPrice * hr * user.getDiscount()) / 100);
        if (rate.equals(Rate.SUBSCRIPTION)) {
            if ((user.getSubHours() - hr) < 0) user.setUserStatus(UserStatus.BANNED);
            user.setSubHours(user.getSubHours() - hr);
            userDao.save(user);
            return 0.0;
        } else {
            return sum;
        }
    }

    @Override
    @Transactional
    public Double closeScooterRental(Order order, Double mileage, Long newLocationId) {
        Long scooterId = order.getScooterId();
        Scooter scooter = scooterDao.getById(scooterId);
        scooter.setMileage(scooter.getMileage() + mileage);
        scooter.setLocation(locationDao.getById(newLocationId));
        scooterDao.save(scooter);
        return scooter.getPrice();
    }

}
