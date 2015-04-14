package tchepannou.mora.rest.core.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import tchepannou.mora.core.domain.AccessToken;

import java.util.Collection;

public class TokenAuthentication extends PreAuthenticatedAuthenticationToken{
    public TokenAuthentication(Object aPrincipal) {
        super(aPrincipal, null);
    }
    public TokenAuthentication(Object aPrincipal, Collection<? extends GrantedAuthority> authorities) {
        super(aPrincipal, null, authorities);
    }
    public TokenAuthentication(AccessToken token, Collection<? extends GrantedAuthority> authorities) {
        super(token.getValue(), null, authorities);
    }
}
