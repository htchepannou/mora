package tchepannou.mora.core.dao.jdbc;

import tchepannou.mora.core.dao.UserDao;
import tchepannou.mora.core.dao.jdbc.mapper.UserMapper;
import tchepannou.mora.core.domain.User;

public class JdbcUserDao extends JdbcModelDao implements UserDao {
    //-- Attributes
    private static final UserMapper MAPPER = new UserMapper();

    //-- UserDao overrides
    @Override
    public User findByEmail(String email) {
        try {
            return template.queryForObject("SELECT * FROM t_user WHERE email=?", new Object[]{email}, MAPPER);
        } catch (org.springframework.dao.EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public User findById(long id) {
        try {
            return template.queryForObject("SELECT * FROM t_user WHERE id=?", new Object[] {id}, MAPPER);
        } catch (org.springframework.dao.EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public User findByUsername(String username) {
        try {
            return template.queryForObject("SELECT * FROM t_user WHERE username=?", new Object[] {username}, MAPPER);
        } catch (org.springframework.dao.EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public void save(User user) {
        if (user.isTransient()){
            String sql = "INSERT INTO user(username, email, firstname, lastname, deleted, creation_date, last_update) VALUES(?,?,?,?,?,?,?)";
            template.update(sql, user.getUsername(), user.getEmail(), user.getFirstName(), user.getLastUpdate(), user.isDeleted(), user.getCreationDate(), user.getLastUpdate());
        } else {
            String sql = "UPDATE t_user SET username=?, email=?, firstname=?, lastname=?, deleted=?, creation_date=?, last_update=? WHERE id=?";
            template.update(sql, user.getUsername(), user.getEmail(), user.getFirstName(), user.getLastUpdate(), user.isDeleted(), user.getCreationDate(), user.getLastUpdate(), user.getId());
        }

    }
}
