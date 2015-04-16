package tchepannou.mora.insidesoccer.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.core.domain.AccessToken;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IsAccessTokenRowMapper implements RowMapper<AccessToken> {
    @Override
    public AccessToken mapRow(ResultSet rs, int i) throws SQLException {
        AccessToken result = new AccessToken ();

        result.setId(rs.getLong("login_id"));
        result.setUserId(rs.getLong("login_party_fk"));
        result.setCreationDate(rs.getTimestamp("login_date"));
        result.setExpiryDate(rs.getTimestamp("login_logout_date"));
        result.setValue(computeHash(result));

        return result;
    }

    private String computeHash (AccessToken token){
        return String.valueOf(token.getId());
    }
}
