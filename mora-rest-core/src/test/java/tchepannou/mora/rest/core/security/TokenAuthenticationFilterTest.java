package tchepannou.mora.rest.core.security;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith (MockitoJUnitRunner.class)
public class TokenAuthenticationFilterTest {

    @Mock
    private HttpServletRequest req;

    @Mock
    private HttpServletResponse resp;

    @Mock
    private FilterChain chain;

    @Mock
    private AuthenticationManager authenticationManager;

    private TokenAuthenticationFilter filter;


    @Before
    public void setUp (){
        filter = new TokenAuthenticationFilter(authenticationManager);
    }

    @Test
    public void testDoFilter() throws Exception {
        // Given
        when(req.getHeader(SecurityContants.X_AUTH_TOKEN.name())).thenReturn("123");

        Authentication auth = mock(Authentication.class);
        when(auth.isAuthenticated()).thenReturn(true);

        when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(auth);

        // When
        filter.doFilter(req, resp, chain);

        // Then
        Authentication result = SecurityContextHolder.getContext().getAuthentication();
        assertThat(result, equalTo(auth));
    }

    @Test
    public void testDoFilter_autheticnationReturnsUnauthenticated_returns401() throws Exception {
        // Given
        when(req.getHeader(SecurityContants.X_AUTH_TOKEN.name())).thenReturn("123");

        Authentication auth = mock(Authentication.class);
        when(auth.isAuthenticated()).thenReturn(false);

        when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(auth);

        // When
        filter.doFilter(req, resp, chain);

        // Then
        ArgumentCaptor<Integer> code = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<String> msg = ArgumentCaptor.forClass(String.class);
        verify(resp).sendError(code.capture(), msg.capture());
        assertThat(code.getValue(), equalTo(HttpServletResponse.SC_UNAUTHORIZED));

        Authentication result = SecurityContextHolder.getContext().getAuthentication();
        assertThat(result, nullValue());
    }

    @Test
    public void testDoFilter_authenticationReturnsNull_returns401() throws Exception {
        // Given
        when(req.getHeader(SecurityContants.X_AUTH_TOKEN.name())).thenReturn("123");

        Authentication auth = mock(Authentication.class);
        when(auth.isAuthenticated()).thenReturn(false);

        when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(null);

        // When
        filter.doFilter(req, resp, chain);

        // Then
        ArgumentCaptor<Integer> code = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<String> msg = ArgumentCaptor.forClass(String.class);
        verify(resp).sendError(code.capture(), msg.capture());
        assertThat(code.getValue(), equalTo(HttpServletResponse.SC_UNAUTHORIZED));

        Authentication result = SecurityContextHolder.getContext().getAuthentication();
        assertThat(result, nullValue());
    }

    @Test
    public void testDoFilter_aoTokenInRequestHeader_returns401() throws Exception {
        // Given
        when(req.getHeader(SecurityContants.X_AUTH_TOKEN.name())).thenReturn("123");

        Authentication auth = mock(Authentication.class);
        when(auth.isAuthenticated()).thenReturn(false);

        when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(null);

        // When
        filter.doFilter(req, resp, chain);

        // Then
        ArgumentCaptor<Integer> code = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<String> msg = ArgumentCaptor.forClass(String.class);
        verify(resp).sendError(code.capture(), msg.capture());
        assertThat(code.getValue(), equalTo(HttpServletResponse.SC_UNAUTHORIZED));

        Authentication result = SecurityContextHolder.getContext().getAuthentication();
        assertThat(result, nullValue());
    }
}