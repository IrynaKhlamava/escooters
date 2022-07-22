package com.company.dao;

import com.company.api.dao.IRoleDao;
import com.company.model.Role;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDao extends AbstractDao<Role> implements IRoleDao {

    @Override
    protected Class<Role> getClazz() {
        return Role.class;
    }

}
