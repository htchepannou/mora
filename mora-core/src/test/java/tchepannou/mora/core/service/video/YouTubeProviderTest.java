package tchepannou.mora.core.service.video;

import org.junit.Test;
import tchepannou.mora.core.service.VideoProvider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class YouTubeProviderTest {
    private VideoProvider service = new YouTubeProvider();


    @Test
    public void testGetEmbedUrl() throws Exception {
        assertEquals("https://www.youtube.com/embed/123", service.getEmbedUrl("123"));
    }

    @Test
    public void testGetVideoId() throws Exception {
        String expected = "XOcCOBe8PTc";

        assertEquals(expected, service.getVideoId("https://www.youtube.com/embed/XOcCOBe8PTc"));
        assertEquals(expected, service.getVideoId("http://www.youtube.com/embed/XOcCOBe8PTc"));
        assertEquals(expected, service.getVideoId("https://www.youtube.com/embed/XOcCOBe8PTc?hd=1&rel=0&wmode=transparent"));

        assertEquals(expected, service.getVideoId("https://www.youtube-nocookie.com/embed/XOcCOBe8PTc?hd=1&wmode=opaque&rel=0"));
        assertEquals(expected, service.getVideoId("https://www.youtube-nocookie.com/embed/XOcCOBe8PTc?hd=1&rel=0&wmode=transparent"));

        assertEquals(expected, service.getVideoId("https://www.youtube.com/watch?v=XOcCOBe8PTc"));
        assertEquals(expected, service.getVideoId("http://www.youtube.com/watch?v=XOcCOBe8PTc"));

        assertEquals(expected, service.getVideoId("https://youtu.be/XOcCOBe8PTc"));
        assertEquals(expected, service.getVideoId("http://youtu.be/XOcCOBe8PTc"));

        assertNull(service.getVideoId("https://www.youtube.com/user/12121"));
    }
}