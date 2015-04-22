package tchepannou.mora.core.dao.jdbc;

import tchepannou.mora.core.dao.RoleDao;
import tchepannou.mora.core.domain.Role;

public class JdbcRoleDao extends JdbcEnumModelDao<Role> implements RoleDao{
    public JdbcRoleDao() {
        super(Role.class);
    }

    //-- JdbcModelDao overrides
    @Override
    protected String getTableName() {
        return "t_role";
    }
}
