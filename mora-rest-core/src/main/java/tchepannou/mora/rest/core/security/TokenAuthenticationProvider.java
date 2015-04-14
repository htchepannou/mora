package tchepannou.mora.rest.core.security;

import com.google.common.base.Preconditions;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;
import tchepannou.mora.core.domain.*;
import tchepannou.mora.core.service.AccessTokenService;
import tchepannou.mora.core.service.UserService;

public class TokenAuthenticationProvider implements AuthenticationProvider{
    //-- Attributes
    private AccessTokenService accessTokenService;
    private UserService userService;


    //-- Constructor
    public TokenAuthenticationProvider(AccessTokenService accessTokenService, UserService userService){
        Preconditions.checkArgument(accessTokenService != null , "accessTokenService == null");
        Preconditions.checkArgument(userService != null , "userService == null");

        this.accessTokenService = accessTokenService;
        this.userService = userService;
    }


    //-- AuthenticationProvider overrides
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        /* get token */
        String token = authentication.getName();
        AccessToken accessToken = accessTokenService.findByValue(token);
        if (accessToken == null){
            throw new BadCredentialsException(token + " is not a valid token");
        } else if (accessToken.isExpired()){
            throw new CredentialsExpiredException(token + " has expired");
        }

        /* user */
        User user = userService.findById(accessToken.getId());
        if (user == null){
            throw new PreAuthenticatedCredentialsNotFoundException(token + " is associated with no user!");
        }

        Authentication result = new TokenAuthentication(accessToken, AuthorityUtils.createAuthorityList(tchepannou.mora.core.domain.Role.AUTHORITY_ROLE_USER));
        result.setAuthenticated(true);
        return result;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return TokenAuthentication.class.equals(aClass);
    }
}
