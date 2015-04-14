package tchepannou.mora.core.dao.jdbc;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.core.dao.RoleDao;
import tchepannou.mora.core.dao.jdbc.mapper.RoleRowMapper;
import tchepannou.mora.core.domain.Role;

import java.util.List;

public class JdbcRoleDao extends JdbcReadOnlyModelDao<Role> implements RoleDao{
    private static final RowMapper<Role> MAPPER = new RoleRowMapper();

    //-- JdbcModelDao overrides
    @Override
    protected String getTableName() {
        return "t_role";
    }

    @Override
    protected RowMapper<Role> getRowMapper() {
        return MAPPER;
    }

    @Override
    public List<Role> findAll() {
        return template.query("SELECT * FROM t_role", new Object[]{}, MAPPER);
    }
}
