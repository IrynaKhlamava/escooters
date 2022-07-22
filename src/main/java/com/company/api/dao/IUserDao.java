package com.company.api.dao;

import com.company.model.AppUser;

import java.util.List;

public interface IUserDao {

    AppUser save(AppUser user);

    void update(Long id, AppUser updateData);

    List<AppUser> getAll(String col);

    AppUser getById(Long id);

    AppUser findByUsername(String username);

    AppUser checkUserByUsername(String login);

    void delete(AppUser user);

}
