package tchepannou.mora.core.dao.jdbc;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import tchepannou.mora.core.dao.PasswordDao;
import tchepannou.mora.core.dao.jdbc.mapper.PasswordMapper;
import tchepannou.mora.core.domain.Password;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;


public class JdbcPasswordDao extends JdbcModelDao implements PasswordDao {
    //-- Attributes
    private static final PasswordMapper MAPPER = new PasswordMapper();

    //-- PasswordDao overrides
    @Override
    public Password findByUser(long userId) {
        try{
            String sql = "SELECT * FROM t_password WHERE user_id=?";
            return template.queryForObject(sql, new Object[] {userId}, MAPPER);
        } catch (org.springframework.dao.EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public void save(final Password password) {
        if (password.isTransient()){
            KeyHolder holder = new GeneratedKeyHolder();

            final String sql = "INSERT INTO t_password(value, user_id, creation_date, last_update) VALUES(?,?,?,?)";
            PreparedStatementCreator ps = new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, password.getValue());
                    ps.setLong(2, password.getUserId());
                    ps.setTimestamp(3, new Timestamp(password.getCreationDate().getTime()));
                    ps.setTimestamp(4, new Timestamp(password.getLastUpdate().getTime()));
                    return ps;
                }
            };
            template.update(ps, holder);

            password.setId(holder.getKey().longValue());
        } else {
            String sql = "UPDATE t_password SET value=?, user_id=?, creation_date=?, last_update=? WHERE id=?";
            template.update(sql, password.getValue(), password.getUserId(), password.getCreationDate(), password.getLastUpdate(), password.getId());
        }
    }

}
