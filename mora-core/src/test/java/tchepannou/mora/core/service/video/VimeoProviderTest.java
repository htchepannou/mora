package tchepannou.mora.core.service.video;

import org.junit.Test;
import tchepannou.mora.core.service.VideoProvider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class VimeoProviderTest {
    private VideoProvider service = new VimeoProvider();


    @Test
    public void testGetEmbedUrl() throws Exception {
        assertEquals("https://player.vimeo.com/video/123", service.getEmbedUrl("123"));
    }

    @Test
    public void testGetVideoId() throws Exception {
        String expected = "100949626";

        assertEquals(expected, service.getVideoId("https://Vimeo.com/100949626"));
        assertEquals(expected, service.getVideoId("https://Vimeo.com/channels/112171/100949626"));

        assertEquals(expected, service.getVideoId("http://Vimeo.com/100949626"));
        assertEquals(expected, service.getVideoId("https://Vimeo.com/channels/112171/100949626"));
        assertNull(service.getVideoId("https://unknown.com/video/XOcCOBe8PTc?syndication=131181"));
    }
}