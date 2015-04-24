package tchepannou.mora.insidesoccer.dao.impl;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.core.dao.RoleDao;
import tchepannou.mora.core.domain.Role;
import tchepannou.mora.insidesoccer.dao.mapper.IsRoleRowMapper;
import tchepannou.mora.insidesoccer.domain.IsRole;

import java.util.List;

public class IsRoleDao extends IsReadOnlyModelDao<IsRole> implements RoleDao {
    private static final RowMapper<IsRole> MAPPER = new IsRoleRowMapper();

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
    protected RowMapper<IsRole> getRowMapper() {
        return MAPPER;
    }

    //-- Role overrides
    @Override
    public List<Role> findAll() {
        List<IsRole> result = template.query("SELECT * FROM role", new Object[]{}, MAPPER);
        return (List)result;
    }

}
