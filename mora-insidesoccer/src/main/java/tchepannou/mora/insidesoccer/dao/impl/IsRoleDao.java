package tchepannou.mora.insidesoccer.dao.impl;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.core.dao.RoleDao;
import tchepannou.mora.core.domain.Role;
import tchepannou.mora.insidesoccer.dao.mapper.IsRoleRowMapper;

import java.util.List;

public class IsRoleDao extends IsReadOnlyModelDao<Role> implements RoleDao {
    private static final RowMapper<Role> MAPPER = new IsRoleRowMapper();

    //-- JdbcIsReadOnlyModelDao overrides
    @Override
    protected String getTableName() {
        return "role";
    }

    @Override
    protected String getIdColumn() {
        return "role_id";
    }

    @Override
    protected RowMapper<Role> getRowMapper() {
        return MAPPER;
    }

    //-- Role overrides
    @Override
    public List<Role> findAll() {
        return template.query("SELECT * FROM role", new Object[]{}, MAPPER);
    }

}
