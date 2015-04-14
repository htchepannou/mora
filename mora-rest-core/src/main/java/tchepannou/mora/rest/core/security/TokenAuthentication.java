package tchepannou.mora.rest.core.security;

import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

public class TokenAuthentication extends PreAuthenticatedAuthenticationToken{
    public TokenAuthentication(Object aPrincipal) {
        super(aPrincipal, null);
    }
}
