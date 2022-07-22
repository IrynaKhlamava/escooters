package com.company.services;

import com.company.model.AppUser;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUser {

    public static UserDetails fromUser(AppUser user) {
        return new org.springframework.security.core.userdetails.User(
                user.getLogin(), user.getPassword(),user.getRoles()
        );
    }

}
