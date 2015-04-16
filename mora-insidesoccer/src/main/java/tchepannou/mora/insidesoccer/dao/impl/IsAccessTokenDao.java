package tchepannou.mora.insidesoccer.dao.impl;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.core.dao.AccessTokenDao;
import tchepannou.mora.core.domain.AccessToken;
import tchepannou.mora.insidesoccer.dao.mapper.IsAccessTokenRowMapper;

import java.util.List;

public class IsAccessTokenDao extends IsReadOnlyModelDao<AccessToken> implements AccessTokenDao {
    //-- Attributes
    private static final RowMapper<AccessToken> MAPPER = new IsAccessTokenRowMapper();

    //--  AccessTokenDao overrides
    @Override
    public AccessToken findByValue(String key) {
        try {
            long id = Long.parseLong(key);
            return findById(id);
        } catch (NumberFormatException e){
            return null;
        }
    }

    @Override
    public List<AccessToken> findByUserByExpired(long userId, boolean expired) {
        String sql = "SELECT * FROM login WHERE login_party_fk=? AND login_active=?";
        return template.query(sql, new Object[]{userId, !expired}, MAPPER);
    }

    @Override
    public void update(List<AccessToken> tokens) {
        throw new UnsupportedOperationException();
    }

    //-- JdbcIsReadOnlyModelDao
    @Override
    protected String getIdColumn() {
        return "login_id";
    }

    @Override
    protected String getTableName() {
        return "login";
    }

    @Override
    protected RowMapper<AccessToken> getRowMapper() {
        return MAPPER;
    }
}
