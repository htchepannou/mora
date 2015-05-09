package tchepannou.mora.rest.post.dto;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import tchepannou.mora.core.domain.Media;
import tchepannou.mora.core.domain.MediaType;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.rest.core.dto.EnumDto;

import java.util.Date;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MediaDtoTest {
    Date now = new Date ();
    Date yesterday = DateUtils.addDays(now, -1);
    MediaType mediaType = new MediaType(MediaType.IMAGE, "image");
    Media media = new Media (100, new Space(1), new User(10), mediaType);

    @Before
    public void setUp (){
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
    }

    @Test
    public void testBuilder(){
        // When
        MediaDto result = new MediaDto.Builder()
                .withMedia(media)
                .withMediaType(mediaType)
                .build();

        // Then
        EnumDto mediaTypeDto = new EnumDto.Builder().withEnum(mediaType).build();

        assertThat(result.getTitle(), equalTo(media.getTitle()));
        assertThat(result.getDescription(), equalTo(media.getDescription()));
        assertThat(result.getThumbnailUrl(), equalTo(media.getThumbnailUrl()));
        assertThat(result.getImageUrl(), equalTo(media.getImageUrl()));
        assertThat(result.getUrl(), equalTo(media.getUrl()));
        assertThat(result.getSize(), equalTo(media.getSize()));
        assertThat(result.getContentType(), equalTo(media.getContentType()));
        assertThat(result.isOembed(), equalTo(media.isOembed()));
        assertThat(result.getType(), equalTo(mediaTypeDto));
        assertThat(result.getEmbedUrl(), nullValue());
    }
    @Test
    public void testBuilder_Oembed (){
        // Given
        media.setOembed(true);

        // When
        MediaDto result = new MediaDto.Builder()
                .withMedia(media)
                .withMediaType(mediaType)
                .withEmbedUrl("http://www.google.ca/embed/url.png")
                .build();

        // Then
        EnumDto mediaTypeDto = new EnumDto.Builder().withEnum(mediaType).build();

        assertThat(result.getTitle(), equalTo(media.getTitle()));
        assertThat(result.getDescription(), equalTo(media.getDescription()));
        assertThat(result.getThumbnailUrl(), equalTo(media.getThumbnailUrl()));
        assertThat(result.getImageUrl(), equalTo(media.getImageUrl()));
        assertThat(result.getUrl(), equalTo(media.getUrl()));
        assertThat(result.getSize(), equalTo(media.getSize()));
        assertThat(result.getContentType(), equalTo(media.getContentType()));
        assertThat(result.isOembed(), equalTo(media.isOembed()));
        assertThat(result.getType(), equalTo(mediaTypeDto));
        assertThat(result.getEmbedUrl(), equalTo("http://www.google.ca/embed/url.png"));
    }

    @Test(expected = IllegalStateException.class)
    public void testBuilder_noMedia_throwsIllegalStateException() {
        // When
        new MediaDto.Builder()
                .withMedia(null)
                .withMediaType(mediaType)
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuilder_noMediaType_throwsIllegalStateException() {
        // When
        new MediaDto.Builder()
                .withMedia(media)
                .withMediaType(null)
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuilder_badMediaType_throwsIllegalStateException() {
        // When
        new MediaDto.Builder()
                .withMedia(media)
                .withMediaType(new MediaType(999, "foo"))
                .build();
    }
}