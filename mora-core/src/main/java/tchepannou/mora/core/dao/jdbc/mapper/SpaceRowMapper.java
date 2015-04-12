package tchepannou.mora.core.dao.jdbc.mapper;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.core.domain.Space;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SpaceRowMapper implements RowMapper<Space> {
    @Override
    public Space mapRow(ResultSet rs, int i) throws SQLException {
        Space result = new Space();
        result.setId(rs.getLong("id"));
        result.setUserId(rs.getLong("user_id"));
        result.setTypeId(rs.getLong("space_type_id"));
        result.setName(rs.getString("name"));
        result.setDescription(rs.getString("description"));
        result.setLogoUrl(rs.getString("logo_url"));
        result.setWebsiteUrl(rs.getString("website_url"));
        result.setEmail(rs.getString("email"));
        result.setAddress(rs.getString("address"));
        result.setDeleted(rs.getBoolean("deleted"));
        result.setLastUpdate(rs.getTimestamp("last_update"));
        result.setCreationDate(rs.getTimestamp("creation_date"));
        return result;
    }
}
