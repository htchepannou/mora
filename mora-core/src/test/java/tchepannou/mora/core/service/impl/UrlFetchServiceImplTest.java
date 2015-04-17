package tchepannou.mora.core.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tchepannou.mora.core.service.HttpRequest;
import tchepannou.mora.core.service.HttpResponse;
import tchepannou.mora.core.service.UrlFetchService;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UrlFetchServiceImplTest {
    @Mock
    private HttpRequest request;

    @Mock
    HttpURLConnection cnn;

    @InjectMocks
    private UrlFetchService service = new UrlFetchServiceImpl();

    //-- Test
    @Before
    public void setUp (){
    }

    @Test
    public void testFetch() throws Exception {
        // Given
        InputStream in = mock(InputStream.class);
        when(cnn.getContentType()).thenReturn("text/xml");
        when(cnn.getInputStream()).thenReturn(in);
        when(cnn.getResponseCode()).thenReturn(200);

        when(request.getCharset()).thenReturn("utf-8");
        when(request.getReferer()).thenReturn("http://www.google.ca");
        when(request.getTimeout()).thenReturn(5000);
        when(request.getUrl()).thenReturn(new URL("http://www.yahoo.com"));
        when(request.getUserAgent()).thenReturn("test;user/agent");
        when(request.openConnection()).thenReturn(cnn);

        // When
        HttpResponse result = service.fetch(request);

        // Then
        verify(cnn).setRequestProperty(UrlFetchServiceImpl.HEADER_ACCEPT_CHARSET, "utf-8");
        verify(cnn).setRequestProperty(UrlFetchServiceImpl.HEADER_REFERER, "http://www.google.ca");
        verify(cnn).setRequestProperty(UrlFetchServiceImpl.HEADER_USER_AGENT, "test;user/agent");

        assertThat(result.getStatusCode(), equalTo(200));
        assertThat(result.getContentType(), equalTo("text/xml"));
        assertThat(result.getInputStream(), equalTo(in));
    }

    @Test(expected = IOException.class)
    public void testFetch_connectionThrowsIOException_shouldThrowIOException() throws Exception {
        // Given
        when(request.getCharset()).thenReturn("text/xml");
        when(request.getReferer()).thenReturn("http://www.google.ca");
        when(request.getTimeout()).thenReturn(5000);
        when(request.getUrl()).thenReturn(new URL("http://www.yahoo.com"));
        when(request.getUserAgent()).thenReturn("test;user/agent");
        when(request.openConnection()).thenThrow(IOException.class);

        // When
        service.fetch(request);
    }
}