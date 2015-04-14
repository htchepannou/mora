package tchepannou.mora.rest.core.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith (MockitoJUnitRunner.class)
public class AuthenticationEntryPointImplTest {

    @Mock
    private HttpServletRequest req;

    @Mock
    private HttpServletResponse resp;

    //-- Test
    @Test
    public void testCommence() throws Exception {
        // Given
        AuthenticationException e = mock(AuthenticationException.class);

        // When
        new AuthenticationEntryPointImpl().commence(req, resp, e);

        // Then
        verify(resp).sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}