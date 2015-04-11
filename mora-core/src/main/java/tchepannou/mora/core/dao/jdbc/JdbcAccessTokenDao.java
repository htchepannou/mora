package tchepannou.mora.core.dao.jdbc;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.core.dao.AccessTokenDao;
import tchepannou.mora.core.dao.jdbc.mapper.AccessTokenMapper;
import tchepannou.mora.core.domain.AccessToken;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JdbcAccessTokenDao extends JdbcModelDao<AccessToken> implements AccessTokenDao {
    //-- Attributes
    private static final AccessTokenMapper MAPPER = new AccessTokenMapper();


    //-- JdbcModelDao overrides
    @Override
    protected PreparedStatementCreator getPreparedStatementCreator(final AccessToken model) {
        final String sql = "INSERT INTO t_access_token(value, user_id, creation_date, expiry_date) VALUES(?,?,?,?)";
        return new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, model.getValue());
                ps.setLong(2, model.getUserId());
                ps.setTimestamp(3, new Timestamp(model.getCreationDate().getTime()));
                ps.setTimestamp(4, new Timestamp(model.getExpiryDate().getTime()));
                return ps;
            }
        };
    }

    @Override
    protected String getTableName() {
        return "t_access_token";
    }

    @Override
    protected RowMapper<AccessToken> getRowMapper() {
        return MAPPER;
    }

    //-- AccessTokenDao overrides
    @Override
    public AccessToken findByValue(String value) {
        try {
            return template.queryForObject("SELECT * FROM t_access_token WHERE value=?", new Object[] {value}, MAPPER);
        } catch (org.springframework.dao.EmptyResultDataAccessException e){ // NOSONAR - Each exception intentionally
            return null;
        }
    }

    @Override
    public List<AccessToken> findByUserByExpired(long userId, boolean expired) {
        String sql = expired
                ? "SELECT * FROM t_access_token WHERE user_id=? AND expiry_date<=?"
                : "SELECT * FROM t_access_token WHERE user_id=? AND expiry_date>?";
        
        return template.query(sql, new Object[]{userId, new Date ()}, MAPPER);
    }

    @Override
    public void update(AccessToken token) {
        String sql = "UPDATE t_access_token SET expiry_date=? WHERE id=?";
        template.update(sql, token.getExpiryDate(), token.getId());
    }

    @Override
    public void update(List<AccessToken> tokens) {
        String sql = "UPDATE t_access_token SET expiry_date=? WHERE id=?";
        List<Object[]> args = new ArrayList<Object[]>();
        for (AccessToken token : tokens){
            args.add(new Object[] {token.getExpiryDate(), token.getId()});
        }
        template.batchUpdate(sql, args);
    }
}
