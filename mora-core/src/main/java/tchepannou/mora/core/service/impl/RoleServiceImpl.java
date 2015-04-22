package tchepannou.mora.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tchepannou.mora.core.dao.EnumModelDao;
import tchepannou.mora.core.dao.RoleDao;
import tchepannou.mora.core.domain.Role;
import tchepannou.mora.core.service.RoleService;

@Service
public class RoleServiceImpl extends EnumServiceImpl<Role> implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    protected EnumModelDao<Role> getDao() {
        return roleDao;
    }
}
