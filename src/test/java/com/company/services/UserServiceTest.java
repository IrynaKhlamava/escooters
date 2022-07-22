package com.company.services;

import com.company.api.dao.IRoleDao;
import com.company.api.dao.IUserDao;
import com.company.api.service.IUserService;
import com.company.model.AppUser;
import com.company.model.dto.UserDto;
import com.company.util.MapperUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private IUserDao userDao;
    private IRoleDao roleDao;
    private ModelMapper mapper;
    private MapperUtil mapperUtil;
    private IUserService userService;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void init() {
        userDao = Mockito.mock(IUserDao.class);
        roleDao = Mockito.mock(IRoleDao.class);
        mapperUtil = Mockito.mock(MapperUtil.class);
        mapper = Mockito.mock(ModelMapper.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        userService = new UserService(userDao, roleDao, mapper, mapperUtil, passwordEncoder);
    }

    @Test
    void addUser() {
        when(userDao.checkUserByUsername("login")).thenReturn(null);
        when(passwordEncoder.encode("password")).thenReturn("password");
        userService.addUser("name", "phone", "login", "password");
        verify(userDao, times(1)).checkUserByUsername("login");
        verify(passwordEncoder, times(1)).encode("password");
    }


    @Test
    void update() {
        AppUser userApp = new AppUser();
        UserDto userDto = new UserDto();
        when(mapper.map(userDto, AppUser.class)).thenReturn(userApp);
        userService.update(1L, userDto);
        verify(userDao, times(1)).update(1L, userApp);
    }

    @Test
    void getAll() {
        List<AppUser> appUsers = new ArrayList<>();
        List<UserDto> userDtoList = new ArrayList<>();
        when(userDao.getAll("")).thenReturn(appUsers);
        when(mapperUtil.convertList(appUsers, UserDto.class)).thenReturn(userDtoList);
        assertEquals(userDtoList, userService.getAll(""));
        verify(userDao, times(1)).getAll("");

    }

    @Test
    void delete() {
        AppUser user = new AppUser();
        when(userDao.getById(anyLong())).thenReturn(user);
        userDao.delete(user);
        verify(userDao, times(1)).delete(user);
    }

}