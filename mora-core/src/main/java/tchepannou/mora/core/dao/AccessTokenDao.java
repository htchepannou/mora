package tchepannou.mora.core.dao;

import tchepannou.mora.core.domain.AccessToken;

import java.util.List;

public interface AccessTokenDao {
    AccessToken findByValue(String key);
    List<AccessToken> findByUserByExpired (long userId, boolean expired);
    long create (AccessToken token);
    void update (AccessToken token);
    void update (List<AccessToken> tokens);
}
