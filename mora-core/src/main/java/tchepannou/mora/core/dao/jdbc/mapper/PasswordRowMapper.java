package tchepannou.mora.core.dao.jdbc.mapper;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.core.domain.Password;

import java.sql.ResultSet;
import java.sql.SQLException;

//-- Inner
public final class PasswordRowMapper implements RowMapper<Password> {
    @Override
    public Password mapRow(ResultSet rs, int i) throws SQLException {
        Password password = new Password();

        password.setId (rs.getLong("id"));
        password.setCreationDate(rs.getTimestamp("creation_date"));
        password.setLastUpdate(rs.getTimestamp("last_update"));
        password.setValue(rs.getString("value"));
        password.setUserId(rs.getLong("user_id"));

        return password;
    }
}
