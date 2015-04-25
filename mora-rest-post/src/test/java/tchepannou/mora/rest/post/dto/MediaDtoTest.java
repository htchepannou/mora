package tchepannou.mora.rest.post.dto;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import tchepannou.mora.core.domain.Media;
import tchepannou.mora.core.domain.MediaType;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.User;

import java.util.Date;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class MediaDtoTest {
    @Test
    public void testBuilder (){
        // Given
        Date now = new Date ();
        Date yesterday = DateUtils.addDays(now, -1);

        Space space = new Space(1);
        User user = new User(10);
        MediaType type = new MediaType(MediaType.IMAGE, "image");


        Media media = new Media (100, space, user, type);
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
                .withMediaType(type)
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
        assertThat(result.getTypeId(), equalTo(media.getTypeId()));
    }
}