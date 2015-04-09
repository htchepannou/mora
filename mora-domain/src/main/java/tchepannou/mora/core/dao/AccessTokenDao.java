package tchepannou.mora.core.dao;

import tchepannou.mora.core.domain.AccessToken;

import java.util.List;

public interface AccessTokenDao {
    AccessToken findById (long id);
    List<AccessToken> findActiveByUser (long userId);
    void save (AccessToken token);
    void save (List<AccessToken> tokens);
}
