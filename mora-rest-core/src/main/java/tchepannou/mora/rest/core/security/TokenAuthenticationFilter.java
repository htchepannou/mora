package tchepannou.mora.rest.core.security;

import com.google.common.base.Preconditions;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends GenericFilterBean {
    //-- Attributes
    private AuthenticationManager authenticationManager;


    //-- Public
    public TokenAuthenticationFilter(AuthenticationManager authenticationManager){
        Preconditions.checkArgument(authenticationManager != null, "authenticationManager == null");

        this.authenticationManager = authenticationManager;
    }

    //-- GenericFilterBean overrides
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        try {
            String token = req.getHeader(SecurityContants.X_AUTH_TOKEN.name());
            authenticate(token);

            filterChain.doFilter(req, resp);
        } catch (AuthenticationException e){    // NOSONAR - no need to log or rethrow it
            SecurityContextHolder.clearContext();
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        }
    }

    //-- Private
    private void authenticate (String token) {
        Authentication auth;

        if (token == null){
            auth = new AnonymousAuthenticationToken("anonymous", "anonymous", AuthorityUtils.createAuthorityList(tchepannou.mora.core.domain.Role.AUTHORITY_ROLE_ANONYMOUS));
        } else {
            auth = authenticationManager.authenticate(new TokenAuthentication(token, AuthorityUtils.createAuthorityList(tchepannou.mora.core.domain.Role.AUTHORITY_ROLE_USER)));
            if (auth == null || !auth.isAuthenticated()) {
                throw new InternalAuthenticationServiceException("Authentication failed: " + token);
            }
        }

        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
