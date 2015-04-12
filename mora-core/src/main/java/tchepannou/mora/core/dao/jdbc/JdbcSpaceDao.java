package tchepannou.mora.core.dao.jdbc;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.core.dao.SpaceDao;
import tchepannou.mora.core.dao.jdbc.mapper.SpaceRowMapper;
import tchepannou.mora.core.domain.Space;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class JdbcSpaceDao extends JdbcModelDao<Space> implements SpaceDao{
    private static final RowMapper<Space> MAPPER = new SpaceRowMapper();

    //-- SpaceDao overrides
    @Override
    public void update(Space space) {
        String sql = "UPDATE t_space SET name=?, abbreviation=?, description=?, logo_url=?, website_url=?, email=?, address=?, last_update=? WHERE id=?";
        template.update(sql, space.getName(), space.getAbbreviation(), space.getDescription(), space.getLogoUrl(), space.getWebsiteUrl(), space.getEmail(), space.getAddress(), space.getLastUpdate(), space.getId());
    }

    //-- JdbcModelDao overrides
    @Override
    protected PreparedStatementCreator getPreparedStatementCreator(final Space model) {
        final String sql = "INSERT INTO t_space(space_type_id, user_id, name, abbreviation, description, logo_url, website_url, email, address, deleted, creation_date, last_update) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        return new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, model.getTypeId());
                ps.setLong(2, model.getUserId());
                ps.setString(3, model.getName());
                ps.setString(4, model.getAbbreviation());
                ps.setString(5, model.getDescription());
                ps.setString(6, model.getLogoUrl());
                ps.setString(7, model.getWebsiteUrl());
                ps.setString(8, model.getEmail());
                ps.setString(9, model.getAddress());
                ps.setBoolean(10, false);
                ps.setTimestamp(11, new Timestamp(model.getCreationDate().getTime()));
                ps.setTimestamp(12, new Timestamp(model.getLastUpdate().getTime()));
                return ps;
            }
        };
    }

    @Override
    protected String getTableName() {
        return "t_space";
    }

    @Override
    protected RowMapper<Space> getRowMapper() {
        return MAPPER;
    }
}
