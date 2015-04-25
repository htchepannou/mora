package tchepannou.mora.rest.post.dto;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tchepannou.mora.core.domain.Media;
import tchepannou.mora.core.domain.MediaType;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.core.service.MediaTypeService;
import tchepannou.mora.core.service.OembedService;

import java.util.Date;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MediaDtoTest {
    @Mock
    private OembedService oembedService;

    @Mock
    private MediaTypeService mediaTypeService;

    @Test
    public void testBuilder(){
        // Given
        Date now = new Date ();
        Date yesterday = DateUtils.addDays(now, -1);

        MediaType mediaType = new MediaType(MediaType.IMAGE, "image");

        when(oembedService.getEmbedUrl("http://www.google.ca/url.png")).thenReturn("http://www.google.ca/embed/url.png");
        when(mediaTypeService.findById(mediaType.getId())).thenReturn(mediaType);

        Media media = new Media (100, new Space(1), new User(10), mediaType);
        media.setTitle( "title1");
        media.setDescription("bar");;
        media.setUrl("http://www.google.ca/url.png");
        media.setImageUrl("http://www.google.ca/img.png");
        media.setThumbnailUrl("http://www.google.ca/thumb.png");
        media.setContentType("image/png");
        media.setOembed(false);
        media.setSize(1000);
        media.setLastUpdate(now);
        media.setCreationDate(yesterday);

        // When
        MediaDto result = new MediaDto.Builder()
                .withMedia(media)
                .withOembedService(oembedService)
                .withMediaTypeService(mediaTypeService)
                .build();

        // Then
        assertThat(result.getTitle(), equalTo(media.getTitle()));
        assertThat(result.getDescription(), equalTo(media.getDescription()));
        assertThat(result.getThumbnailUrl(), equalTo(media.getThumbnailUrl()));
        assertThat(result.getImageUrl(), equalTo(media.getImageUrl()));
        assertThat(result.getUrl(), equalTo(media.getUrl()));
        assertThat(result.getSize(), equalTo(media.getSize()));
        assertThat(result.getContentType(), equalTo(media.getContentType()));
        assertThat(result.isOembed(), equalTo(media.isOembed()));
        assertThat(result.getType(), equalTo(new MediaTypeDto(MediaType.IMAGE, "image")));
        assertThat(result.getEmbedUrl(), nullValue());
    }
    @Test
    public void testBuilder_Oembed (){
        // Given
        Date now = new Date ();
        Date yesterday = DateUtils.addDays(now, -1);

        MediaType mediaType = new MediaType(MediaType.IMAGE, "image");

        when(oembedService.getEmbedUrl("http://www.google.ca/url.png")).thenReturn("http://www.google.ca/embed/url.png");
        when(mediaTypeService.findById(mediaType.getId())).thenReturn(mediaType);

        Media media = new Media (100, new Space(1), new User(10), mediaType);
        media.setTitle( "title1");
        media.setDescription("bar");;
        media.setUrl("http://www.google.ca/url.png");
        media.setImageUrl("http://www.google.ca/img.png");
        media.setThumbnailUrl("http://www.google.ca/thumb.png");
        media.setContentType("image/png");
        media.setOembed(true);
        media.setSize(1000);
        media.setLastUpdate(now);
        media.setCreationDate(yesterday);

        // When
        MediaDto result = new MediaDto.Builder()
                .withMedia(media)
                .withOembedService(oembedService)
                .withMediaTypeService(mediaTypeService)
                .build();

        // Then
        assertThat(result.getTitle(), equalTo(media.getTitle()));
        assertThat(result.getDescription(), equalTo(media.getDescription()));
        assertThat(result.getThumbnailUrl(), equalTo(media.getThumbnailUrl()));
        assertThat(result.getImageUrl(), equalTo(media.getImageUrl()));
        assertThat(result.getUrl(), equalTo(media.getUrl()));
        assertThat(result.getSize(), equalTo(media.getSize()));
        assertThat(result.getContentType(), equalTo(media.getContentType()));
        assertThat(result.isOembed(), equalTo(media.isOembed()));
        assertThat(result.getType(), equalTo(new MediaTypeDto(MediaType.IMAGE, "image")));
        assertThat(result.getEmbedUrl(), equalTo("http://www.google.ca/embed/url.png"));
    }


}