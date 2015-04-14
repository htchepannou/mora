package tchepannou.mora.core.service;

import tchepannou.mora.core.domain.Role;

import java.util.List;

public interface RoleService {
    Role findById(long id);
    List<Role> findAll();
}
