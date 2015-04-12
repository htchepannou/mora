package tchepannou.mora.core.dao.jdbc.mapper;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.core.domain.SpaceType;

import java.sql.ResultSet;
import java.sql.SQLException;


public class SpaceTypeRowMapper implements RowMapper<SpaceType> {
    @Override
    public SpaceType mapRow(ResultSet rs, int i) throws SQLException {
        SpaceType result = new SpaceType();
        result.setId(rs.getLong("id"));
        result.setName(rs.getString("name"));
        return result;
    }
}
