package tchepannou.mora.core.service;

import tchepannou.mora.core.domain.AccessToken;
import tchepannou.mora.core.exception.AuthException;

public interface AuthService {
    AccessToken findByValue(String value);
    AccessToken authenticate (String usernameOrEmail, String clearPassword) throws AuthException;
    void logout(String value);
}
