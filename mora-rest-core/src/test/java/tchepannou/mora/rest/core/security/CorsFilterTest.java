package tchepannou.mora.rest.core.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class CorsFilterTest {
    @Mock
    HttpServletRequest req;

    @Mock
    HttpServletResponse resp;

    @Mock
    FilterChain chain;

    Filter filter = new CorsFilter();

    @Test
    public void testDoFilter() throws Exception {
        // When
        filter.doFilter(req, resp, chain);

        // Then
        verify(chain).doFilter(req, resp);

        verify(resp).setHeader("Access-Control-Allow-Origin", "*");
        verify(resp).setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        verify(resp).setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept," + SecurityContants.X_AUTH_TOKEN);
    }
}