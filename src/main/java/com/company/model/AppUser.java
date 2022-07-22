package com.company.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class AppUser extends AEntity implements UserDetails {

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "login")
    private String login;

    @Column(name = "hours")
    private Integer hours;

    @Column(name = "sub_hours")
    private Integer subHours;

    @Column(name = "discount")
    private Integer discount;

    @Column(name = "password")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id")
    )
    private Set<Role> roles;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @Transient
    private boolean isActive;

    public AppUser(String name, String phone, String login, String password) {
        this.name = name;
        this.phone = phone;
        this.login = login;
        this.password = password;
    }

    public AppUser(
            String name, String phone, Integer hours, Integer subHours, Integer discount, String login,
            String password, UserStatus userStatus, boolean isActive
    ) {
        this.name = name;
        this.phone = phone;
        this.hours = hours;
        this.subHours = subHours;
        this.discount = discount;
        this.login = login;
        this.password = password;
        this.userStatus = userStatus;
        this.isActive = isActive;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppUser)) return false;
        if (!super.equals(o)) return false;
        AppUser user = (AppUser) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(login, user.login) &&
                Objects.equals(hours, user.hours) &&
                Objects.equals(subHours, user.subHours) &&
                Objects.equals(discount, user.discount) &&
                Objects.equals(password, user.password) &&
                Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, phone, login, hours, subHours, discount, password, roles);
    }

}
