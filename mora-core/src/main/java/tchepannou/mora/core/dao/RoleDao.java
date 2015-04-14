package tchepannou.mora.core.dao;

import tchepannou.mora.core.domain.Role;

import java.util.List;

public interface RoleDao {
    Role findById (long id);
    List<Role> findAll ();
}
