package tchepannou.mora.insidesoccer.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.core.domain.SpaceType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IsSpaceTypeRowMapper implements RowMapper<SpaceType> {
    @Override
    public SpaceType mapRow(ResultSet rs, int i) throws SQLException {
        SpaceType result = new SpaceType();
        result.setId(rs.getLong("ptype_id"));
        result.setName(rs.getString("ptype_name"));
        return result;
    }
}
