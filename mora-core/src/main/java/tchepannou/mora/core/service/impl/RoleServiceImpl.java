package tchepannou.mora.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tchepannou.mora.core.dao.RoleDao;
import tchepannou.mora.core.domain.Role;
import tchepannou.mora.core.service.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    //-- RoleService overrides  ---
    @Override
    @Cacheable("Role")
    public Role findById(long id) {
        return roleDao.findById(id);
    }

    @Override
    @Cacheable ("Role")
    public List<Role> findAll() {
        return roleDao.findAll();
    }
}
