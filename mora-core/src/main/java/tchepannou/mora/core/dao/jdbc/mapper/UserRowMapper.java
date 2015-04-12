package tchepannou.mora.core.dao.jdbc.mapper;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.core.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

//-- Inner
public final class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {
        User user = new User();

        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setFirstName(rs.getString("firstname"));
        user.setLastName(rs.getString("lastname"));
        user.setDeleted(rs.getBoolean("deleted"));
        user.setCreationDate(rs.getTimestamp("creation_date"));
        user.setLastUpdate(rs.getTimestamp("last_update"));

        return user;
    }
}
