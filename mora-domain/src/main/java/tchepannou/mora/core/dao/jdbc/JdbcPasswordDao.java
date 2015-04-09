package tchepannou.mora.core.dao.jdbc;

import tchepannou.mora.core.dao.PasswordDao;
import tchepannou.mora.core.dao.jdbc.mapper.PasswordMapper;
import tchepannou.mora.core.domain.Password;


public class JdbcPasswordDao extends JdbcModelDao implements PasswordDao {
    //-- Attributes
    private static final PasswordMapper MAPPER = new PasswordMapper();

    //-- PasswordDao overrides
    @Override
    public Password findByUser(long userId) {
        try{
            String sql = "SELECT * FROM t_password WHERE user_id=:user_id";
            return template.queryForObject(sql, new Object[] {userId}, MAPPER);
        } catch (org.springframework.dao.EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public void save(Password password) {
        if (password.isTransient()){
            String sql = "INSERT INTO t_password(value, user_id, creation_date, last_update) VALUES(?,?,?,?)";
            template.update(sql, password.getValue(), password.getUserId(), password.getCreationDate(), password.getLastUpdate());
        } else {
            String sql = "UPDATE t_password SET value=?, user_id=?, creation_date=?, last_update=? WHERE id=?";
            template.update(sql, password.getValue(), password.getUserId(), password.getCreationDate(), password.getLastUpdate(), password.getId());
        }
    }

}
