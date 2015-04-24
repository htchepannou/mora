package tchepannou.mora.insidesoccer.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.insidesoccer.domain.IsRole;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IsRoleRowMapper implements RowMapper<IsRole> {
    @Override
    public IsRole mapRow(ResultSet rs, int i) throws SQLException {
        IsRole result = new IsRole();
        result.setId(rs.getLong("role_id"));
        result.setName(rs.getString("role_name"));
        result.setAccessAllTeams(rs.getBoolean("role_access_all_teams"));
        return result;
    }
}
