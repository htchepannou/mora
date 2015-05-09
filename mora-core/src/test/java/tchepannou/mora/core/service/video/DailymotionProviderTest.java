package tchepannou.mora.core.service.video;

import org.junit.Test;
import tchepannou.mora.core.service.VideoProvider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DailymotionProviderTest {
    private VideoProvider service = new DailymotionProvider();

    @Test
    public void testGetEmbedUrl() throws Exception {
        assertEquals("https://www.dailymotion.com/embed/video/123", service.getEmbedUrl("123"));
    }

    @Test
    public void testGetVideoId() throws Exception {
        String expected = "XOcCOBe8PTc";

        assertEquals(expected, service.getVideoId("https://dailymotion.com/embed/video/XOcCOBe8PTc"));
        assertEquals(expected, service.getVideoId("https://dailymotion.com/embed/video/XOcCOBe8PTc?syndication=131181"));
        assertEquals(expected, service.getVideoId("https://dailymotion.com/video/XOcCOBe8PTc?syndication=131181"));
        assertNull(service.getVideoId("https://unknown.com/video/XOcCOBe8PTc?syndication=131181"));
    }
}