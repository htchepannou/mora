package tchepannou.mora.core.service;

import tchepannou.mora.core.domain.AccessToken;
import tchepannou.mora.core.exception.AccessTokenException;

public interface AccessTokenService {
    AccessToken findByValue(String value);
    AccessToken authenticate (String usernameOrEmail, String clearPassword) throws AccessTokenException;
    AccessToken expire(AccessToken token);
}
