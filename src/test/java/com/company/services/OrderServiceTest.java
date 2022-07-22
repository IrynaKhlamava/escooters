package com.company.services;

import com.company.api.dao.ILocationDao;
import com.company.api.dao.IOrderDao;
import com.company.api.dao.IScooterDao;
import com.company.api.dao.IUserDao;
import com.company.api.service.IOrderService;
import com.company.model.*;
import com.company.model.dto.OrderDto;
import com.company.util.MapperUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    private IOrderDao orderDao;
    private IScooterDao scooterDao;
    private IUserDao userDao;
    private ILocationDao locationDao;
    private ModelMapper mapper;
    private MapperUtil mapperUtil;
    private IOrderService orderService;

    @BeforeEach
    public void init() {
        orderDao = Mockito.mock(IOrderDao.class);
        scooterDao = Mockito.mock(IScooterDao.class);
        userDao = Mockito.mock(IUserDao.class);
        locationDao = Mockito.mock(ILocationDao.class);
        mapper = Mockito.mock(ModelMapper.class);
        mapperUtil = Mockito.mock(MapperUtil.class);
        orderService = new OrderService(orderDao, scooterDao, userDao, locationDao, mapper, mapperUtil);
    }

    @Test
    void addOrder() {
        Order order = new Order();
        AppUser appUser = new AppUser();
        OrderDto orderDto = new OrderDto();
        when(userDao.getById(anyLong())).thenReturn(appUser);
        when(mapper.map(order, OrderDto.class)).thenReturn(orderDto);
        when(mapper.map(orderDto, Order.class)).thenReturn(order);
        orderService.addOrder(orderDto);
        verify(mapper, times(1)).map(order, OrderDto.class);
        verify(orderDao, times(1)).save(order);
    }

    @Test
    void update() {
        Order order = new Order();
        OrderDto orderDto = new OrderDto();
        when(mapper.map(orderDto, Order.class)).thenReturn(order);
        orderService.update(1L, orderDto);
        verify(orderDao, times(1)).update(1L, order);
    }

    @Test
    void getAll() {
        List<Order> orders = new ArrayList<>();
        List<OrderDto> orderDtoList = new ArrayList<>();
        when(orderDao.getAll("")).thenReturn(orders);
        when(mapperUtil.convertList(orders, OrderDto.class)).thenReturn(orderDtoList);
        assertEquals(orderDtoList, orderService.getAll(""));
        verify(orderDao, times(1)).getAll("");

    }

    @Test
    void delete() {
        Order order = new Order();
        when(orderDao.getById(anyLong())).thenReturn(order);
        orderDao.delete(order);
        verify(orderDao, times(1)).delete(order);
    }

    @Test
    void getHistory() {
    }

    @Test
    void closeOrder() {
        Order order = new Order(1, "HOURLY", 1L, 1L);
        OrderDto orderDto = new OrderDto();
        Scooter scooter = new Scooter();
        AppUser user = new AppUser("", "", 0, 0, 0, "", "", UserStatus.ACTIVE, true);
        when(orderDao.getById(anyLong())).thenReturn(order);
        when(mapper.map(order, OrderDto.class)).thenReturn(orderDto);
        when(scooterDao.getById(1L)).thenReturn(scooter);
        when(userDao.getById(anyLong())).thenReturn(user);
        order.setScooterId(1L);
        order.setStartDate(LocalDateTime.now());
        user.setId(1L);
        scooter.setPrice(0.0);
        scooter.setMileage(0.0);
        orderService.closeOrder(1L, 12.0, 2L);
        verify(mapper, times(1)).map(order, OrderDto.class);
    }

    @Test
    void closeUserRental() {
        Order order = new Order(1, "HOURLY", 1L, 1L);
        AppUser user = new AppUser("", "", 0, 0, 0, "", "", UserStatus.ACTIVE, true);
        when(orderDao.getById(anyLong())).thenReturn(order);
        when(userDao.getById(anyLong())).thenReturn(user);
        order.setStartDate(LocalDateTime.now());
        assertEquals(10, orderService.closeUserRental(order, 10.0));
        order.setStartDate(LocalDateTime.now().minusHours(1));
        assertEquals(10, orderService.closeUserRental(order, 10.0));
        order.setStartDate(LocalDateTime.now().minusHours(2));
        assertEquals(20, orderService.closeUserRental(order, 10.0));
        order.setStartDate(LocalDateTime.now().minusDays(1));
        assertEquals(240, orderService.closeUserRental(order, 10.0));
        order.setStartDate(LocalDateTime.now().minusDays(2));
        assertEquals(480, orderService.closeUserRental(order, 10.0));
    }

}