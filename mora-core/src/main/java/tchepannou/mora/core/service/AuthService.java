package tchepannou.mora.core.service;

import tchepannou.mora.core.domain.AccessToken;
import tchepannou.mora.core.exception.AuthException;

public interface AuthService {
    AccessToken findByKey (String key);
    AccessToken authenticate (String usernameOrEmail, String clearPassword) throws AuthException;
    void logout(String key);
}