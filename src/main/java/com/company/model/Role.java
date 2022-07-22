package com.company.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role extends AEntity implements GrantedAuthority {

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private RoleValue value;

    @Override
    public String getAuthority() {
        return value.name();
    }

}
