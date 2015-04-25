package tchepannou.mora.core.service.impl;

import org.junit.Test;
import tchepannou.mora.core.service.VideoProvider;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OembedServiceImplTest {
    private OembedServiceImpl service = new OembedServiceImpl();

    @Test
    public void testGetEmbedUrl() throws Exception {
        // Given
        String id = "123243";
        String url = "http://www.youtube.com/watch?id=" + id;
        String expected = "http://www.youtube.com/embed/" + id;

        VideoProvider p1 = mock(VideoProvider.class);

        VideoProvider p2 = mock(VideoProvider.class);
        when(p2.getVideoId(url)).thenReturn(id);
        when(p2.getEmbedUrl(id)).thenReturn(expected);

        VideoProvider p3 = mock(VideoProvider.class);

        service.register(p1);
        service.register(p2);
        service.register(p3);

        // When
        String result = service.getEmbedUrl(url);

        // Then
        assertThat(result, equalTo(expected));
    }


}