package com.company.api.service;

import com.company.model.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    void addUser(String name, String phone, String login, String password);

    void update(Long id, UserDto updateData);

    List<UserDto> getAll(String col);

    UserDto getById(Long id);

    UserDetails loadUserByUsername(String username);

    void delete(Long id);

    void changeUserStatus(Long id, String status);

    void changeUserDiscount(Long id, Integer discount);

}
