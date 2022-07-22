package com.company.services;

import com.company.api.dao.IRoleDao;
import com.company.api.service.IRoleService;
import com.company.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoleService implements IRoleService {

    private final IRoleDao roleDao;

    @Override
    public Role getById(Long id) {
        return roleDao.getById(id);
    }

}
