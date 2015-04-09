package tchepannou.mora.core.dao;

import tchepannou.mora.core.domain.AccessToken;

import java.util.List;

public interface AccessTokenDao {
    AccessToken findByKey (String key);
    List<AccessToken> findByUserByExpired (long userId, boolean expired);
    void create (AccessToken token);
    void update (AccessToken token);
    void update (List<AccessToken> tokens);
}
