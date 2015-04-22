package tchepannou.mora.core.dao.jdbc.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tchepannou.mora.core.domain.Media;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.User;

import java.sql.ResultSet;
import java.sql.Timestamp;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith (MockitoJUnitRunner.class)
public class MediaRowMapperTest {
    @Mock
    private ResultSet rs;

    @Test
    public void testMapRow() throws Exception {
        // Given
        Timestamp now = new Timestamp(System.currentTimeMillis());

        when(rs.getLong("id")).thenReturn(1L);
        when(rs.getLong("user_id")).thenReturn(11L);
        when(rs.getLong("space_id")).thenReturn(12L);
        when(rs.getBoolean("deleted")).thenReturn(true);
        when(rs.getString("title")).thenReturn("title1");
        when(rs.getString("description")).thenReturn("description1");
        when(rs.getString("content_type")).thenReturn("img/png");
        when(rs.getString("url")).thenReturn("http://t.com/foo.png");
        when(rs.getString("thumbnail_url")).thenReturn("http://t.com/foo_small.png");
        when(rs.getString("image_url")).thenReturn("http://t.com/foo_large.png");
        when(rs.getLong("size")).thenReturn(100L);
        when(rs.getBoolean("oembed")).thenReturn(true);
        when(rs.getTimestamp("creation_date")).thenReturn(now);
        when(rs.getTimestamp("last_update")).thenReturn(now);

        // When
        Media result = new MediaRowMapper().mapRow(rs, 0);

        // Then
        Media expected = new Media(1, new Space(12), new User(11));
        expected.setDeleted(true);
        expected.setTitle("title1");
        expected.setDescription("description1");
        expected.setContentType("img/png");
        expected.setOembed(true);
        expected.setSize(100);
        expected.setUrl("http://t.com/foo.png");
        expected.setThumbnailUrl("http://t.com/foo_small.png");
        expected.setImageUrl("http://t.com/foo_large.png");
        expected.setCreationDate(now);
        expected.setLastUpdate(now);
        assertThat(result, equalTo(expected));
    }
}