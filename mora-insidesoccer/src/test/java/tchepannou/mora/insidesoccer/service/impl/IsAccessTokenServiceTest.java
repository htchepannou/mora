package tchepannou.mora.insidesoccer.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tchepannou.mora.core.dao.AccessTokenDao;
import tchepannou.mora.core.domain.AccessToken;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.core.exception.AccessTokenException;
import tchepannou.mora.core.exception.AuthFailedException;
import tchepannou.mora.core.service.HttpRequest;
import tchepannou.mora.core.service.HttpResponse;
import tchepannou.mora.core.service.UrlFetchService;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith (MockitoJUnitRunner.class)
public class IsAccessTokenServiceTest {
    private String api = "http://localhost:9999";

    @Mock
    private AccessTokenDao dao;

    @Mock
    private UrlFetchService urlService;

    @Mock
    private HttpResponse response;

    @Mock
    HttpURLConnection cnn;

    @InjectMocks
    private IsAccessTokenService service = new IsAccessTokenService();


    //-- Test
    @Before
    public void testSetup() throws IOException{
        service.setApiUrl(api);
    }

    @Test
    public void testFindByValue_null_returnsNull() throws Exception {
        assertThat(service.findByValue(null), nullValue());
    }

    public void testFindByValue() throws Exception {
        // Given
        AccessToken token = new AccessToken();
        when(dao.findByValue("toto")).thenReturn(token);

        // When/Then
        assertThat(service.findByValue("toto"), equalTo(token));
    }

    @Test
    public void testExpire() throws Exception {
        // Given
        AccessToken token = new AccessToken(1, new User(1));

        // When
        service.expire(token);

        // Then
        ArgumentCaptor<HttpRequest> captor = ArgumentCaptor.forClass(HttpRequest.class);
        verify(urlService).fetch(captor.capture());

        HttpRequest request = captor.getValue();
        assertThat(request.getUrl(), equalTo(new URL(api + "/login/signout.json?id=" + token.getId())));
    }

    @Test
    public void testAuthenticate() throws Exception {
        // Given
        InputStream in = getClass().getResourceAsStream("/service/testAuthenticate.json");
        when(response.getInputStream()).thenReturn(in);
        when(response.getStatusCode()).thenReturn(200);
        when(urlService.fetch(any(HttpRequest.class))).thenReturn(response);

        AccessToken expected = new AccessToken(100, new User(101));
        when(dao.findByValue("100")).thenReturn(expected);

        // When
        AccessToken result = service.authenticate("foo", "bar");

        // Then
        assertThat(result, equalTo(expected));

        ArgumentCaptor<HttpRequest> captor = ArgumentCaptor.forClass(HttpRequest.class);
        verify(urlService).fetch(captor.capture());
        HttpRequest request = captor.getValue();
        assertThat(request.getUrl(), equalTo(new URL(api + "/login/signin.json?name=foo&password=bar")));

        verify(response).close();
    }

    @Test
    public void testAuthenticate_authFailed_shouldThrowsAuthFailedException() throws Exception {
        // Given
        InputStream in = getClass().getResourceAsStream("/service/testAuthenticate_authFailed.json");
        when(response.getInputStream()).thenReturn(in);
        when(response.getStatusCode()).thenReturn(200);
        when(urlService.fetch(any(HttpRequest.class))).thenReturn(response);

        // When
        try {
            service.authenticate("foo", "bar");
            fail();
        } catch(AuthFailedException e){
            verify(response).close();
        }
    }

    @Test(expected = AccessTokenException.class)
    public void testAuthenticate_statusCodeNot200_throwsAccessTokenException() throws Exception {
        // Given
        when(response.getStatusCode()).thenReturn(500);
        when(urlService.fetch(any(HttpRequest.class))).thenReturn(response);

        // When
        service.authenticate("foo", "bar");
    }

    @Test(expected = AccessTokenException.class)
    public void testAuthenticate_throwsIOException_throwsAccessTokenException() throws Exception {
        // Given
        when(urlService.fetch(any(HttpRequest.class))).thenThrow(IOException.class);

        // When
        service.authenticate("foo", "bar");
    }
}