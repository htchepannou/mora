package tchepannou.mora.core.dao.jdbc;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import tchepannou.mora.core.dao.UserDao;
import tchepannou.mora.core.dao.jdbc.mapper.UserMapper;
import tchepannou.mora.core.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

public class JdbcUserDao extends JdbcModelDao implements UserDao {
    //-- Attributes
    private static final UserMapper MAPPER = new UserMapper();

    //-- UserDao overrides
    @Override
    public User findById(long id) {
        try {
            return template.queryForObject("SELECT * FROM t_user WHERE id=?", new Object[] {id}, MAPPER);
        } catch (org.springframework.dao.EmptyResultDataAccessException e){ // NOSONAR - Each exception intentionally
            return null;
        }
    }

    @Override
    public List<User> findByEmail(String email, boolean deleted) {
        return template.query("SELECT * FROM t_user WHERE email=? AND deleted=?", new Object[]{email, deleted}, MAPPER);
    }

    @Override
    public List<User> findByUsername(String username, boolean deleted) {
        return template.query("SELECT * FROM t_user WHERE username=? AND deleted=?", new Object[] {username, deleted}, MAPPER);
    }

    @Override
    public void save(final User user) {
        if (user.isTransient()){
            KeyHolder holder = new GeneratedKeyHolder();

            final String sql = "INSERT INTO t_user(username, email, firstname, lastname, deleted, creation_date, last_update) VALUES(?,?,?,?,?,?,?)";
            PreparedStatementCreator ps = new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, user.getUsername());
                    ps.setString(2, user.getEmail());
                    ps.setString(3, user.getFirstName());
                    ps.setString(4, user.getLastName());
                    ps.setBoolean(5, user.isDeleted());
                    ps.setTimestamp(6, new Timestamp(user.getCreationDate().getTime()));
                    ps.setTimestamp(7, new Timestamp(user.getLastUpdate().getTime()));
                    return ps;
                }
            };
            template.update(ps, holder);

            user.setId(holder.getKey().longValue());
        } else {
            String sql = "UPDATE t_user SET username=?, email=?, firstname=?, lastname=?, deleted=?, creation_date=?, last_update=? WHERE id=?";
            template.update(sql, user.getUsername(), user.getEmail(), user.getFirstName(), user.getLastName(), user.isDeleted(), user.getCreationDate(), user.getLastUpdate(), user.getId());
        }

    }
}
