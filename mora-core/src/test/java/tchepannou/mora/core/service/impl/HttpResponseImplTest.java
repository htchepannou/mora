package tchepannou.mora.core.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.InputStream;
import java.net.HttpURLConnection;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HttpResponseImplTest {
    @Mock
    private HttpURLConnection cnn;

    @Test
    public void testGetStatusCode() throws Exception {
        // Given
        when(cnn.getResponseCode()).thenReturn(555);

        // When
        int result = new HttpResponseImpl(cnn).getStatusCode();

        // Then
        assertThat(result, equalTo(555));
    }

    @Test
    public void testGetContentType() throws Exception {
        // Given
        when(cnn.getContentType()).thenReturn("text/xml");

        // When
        String result = new HttpResponseImpl(cnn).getContentType();

        // Then
        assertThat(result, equalTo("text/xml"));
    }

    @Test
    public void testGetInputStream() throws Exception {
        // Given
        InputStream in = mock(InputStream.class);
        when(cnn.getInputStream()).thenReturn(in);

        // When
        InputStream result = new HttpResponseImpl(cnn).getInputStream();

        // Then
        assertThat(result, equalTo(in));
    }

    @Test
    public void testClose() throws Exception {
        // When
        new HttpResponseImpl(cnn).close();

        // Then
        verify(cnn).disconnect();
    }
}