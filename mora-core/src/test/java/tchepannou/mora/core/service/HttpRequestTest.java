package tchepannou.mora.core.service;

import org.junit.Test;

import java.net.URL;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class HttpRequestTest {
    @Test
    public void testBuilder() throws Exception{
        HttpRequest req = new HttpRequest.Builder()
                .withCharset("utf-8")
                .withReferer("http://www.google.ca")
                .withTimeout(50)
                .withUrl(new URL("http://www.google.ca"))
                .withUserAgent("foo/bar")
                .build();

        assertThat(req.getCharset(), equalTo("utf-8"));
        assertThat(req.getReferer(), equalTo("http://www.google.ca"));
        assertThat(req.getTimeout(), equalTo(50));
        assertThat(req.getUserAgent(), equalTo("foo/bar"));
        assertThat(req.getUrl(), equalTo(new URL("http://www.google.ca")));
    }
}