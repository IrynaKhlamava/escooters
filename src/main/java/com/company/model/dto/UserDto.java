package com.company.model.dto;

import com.company.model.Role;
import com.company.model.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    private String name;

    private String phone;

    private Integer hours;

    private Integer subHours;

    private Integer discount;

    private String login;

    private String password;

    private Set<Role> roles;

    private UserStatus userStatus;

}
