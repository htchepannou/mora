package tchepannou.mora.insidesoccer.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.core.domain.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IsRoleRowMapper implements RowMapper<Role> {
    @Override
    public Role mapRow(ResultSet rs, int i) throws SQLException {
        Role result = new Role();
        result.setId(rs.getLong("role_id"));
        result.setName(rs.getString("role_name"));
        return result;
    }
}
