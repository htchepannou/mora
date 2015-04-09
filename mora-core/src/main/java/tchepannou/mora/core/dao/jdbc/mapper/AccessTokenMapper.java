package tchepannou.mora.core.dao.jdbc.mapper;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.core.domain.AccessToken;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccessTokenMapper  implements RowMapper<AccessToken> {
    @Override
    public AccessToken mapRow(ResultSet rs, int i) throws SQLException {
        AccessToken result = new AccessToken ();

        result.setId(rs.getLong("id"));
        result.setUserId(rs.getLong("user_id"));
        result.setCreationDate(rs.getTimestamp("creation_date"));
        result.setExpiryDate(rs.getTimestamp("expiry_date"));
        result.setValue(rs.getString("value"));

        return result;
    }
}
