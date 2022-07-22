package com.company.services;

import com.company.api.dao.IRoleDao;
import com.company.api.dao.IUserDao;
import com.company.api.service.IUserService;
import com.company.dao.DaoException;
import com.company.dao.UserDao;
import com.company.model.Order;
import com.company.model.Role;
import com.company.model.AppUser;

import com.company.model.UserStatus;
import com.company.model.dto.UserDto;
import com.company.util.MapperUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Log4j2
@Service
public class UserService implements IUserService {

    private final IUserDao userDao;

    private final IRoleDao roleDao;

    private final ModelMapper mapper;

    private final MapperUtil mapperUtil;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void addUser(String name, String phone, String login, String password) {
        try {
            AppUser userFromDb = userDao.checkUserByUsername(login);
            if (userFromDb != null) {
                log.warn(String.format("User already exists name: %s, phone: %s, login: %s ", name, phone,
                        login));
                throw new ServiceException(String.format("User already exists name: %s, phone: %s, login: %s ", name, phone,
                        login));
            }
            log.info(String.format("addUser by name: %s, phone: %s, login: %s", name, phone,
                    login));

            AppUser newUser = new AppUser(name, phone, 0, 0, 0, login, passwordEncoder.encode(password),
                    UserStatus.ACTIVE, true);
            Set<Role> roles = new HashSet<>();
            roles.add(roleDao.getById(2L));
            newUser.setRoles(roles);
            userDao.save(newUser);
        } catch (DaoException e) {
            log.warn("addUser failed", e);
            throw new ServiceException("addUser failed", e);
        }
    }

    @Override
    @Transactional
    public void update(Long id, UserDto updateData) {
        try {
            updateData.setPassword(passwordEncoder.encode(updateData.getPassword()));
            userDao.update(id, mapper.map(updateData, AppUser.class));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<UserDto> getAll(String col) {
        return mapperUtil.convertList(userDao.getAll(col), this::convertToNoPassUser);
    }

    public UserDto convertToNoPassUser(AppUser user) {
        mapper.typeMap(AppUser.class, UserDto.class).addMappings(mapper -> mapper.skip(UserDto::setPassword));
        return mapper.map(user, UserDto.class);
    }

    @Override
    @Transactional
    public UserDto getById(Long id) {
        AppUser user = userDao.getById(id);
        return convertToNoPassUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        AppUser userFromDB = userDao.findByUsername(username);
        if (userFromDB != null) {
            return SecurityUser.fromUser(userFromDB);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        AppUser user = userDao.getById(id);
        if (user != null) {
            userDao.delete(user);
        }
    }

    @Override
    @Transactional
    public void changeUserStatus(Long id, String status) {
        try {
            AppUser user = userDao.getById(id);
            user.setUserStatus(UserStatus.valueOf(status));
            userDao.update(id, user);
        } catch (Exception e) {
            log.warn("Change user status failed", e);
            throw new DaoException("Change user status failed", e);
        }
    }

    @Override
    @Transactional
    public void changeUserDiscount(Long id, Integer discount) {
        try {
            AppUser user = userDao.getById(id);
            user.setDiscount(discount);
            userDao.update(id, user);
        } catch (Exception e) {
            log.warn("Change user's discount status failed", e);
            throw new DaoException("Change user's discount status failed", e);
        }
    }

}
