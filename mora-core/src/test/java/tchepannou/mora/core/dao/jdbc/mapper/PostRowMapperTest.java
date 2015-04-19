package tchepannou.mora.core.dao.jdbc.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tchepannou.mora.core.domain.Post;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.User;

import java.sql.ResultSet;
import java.sql.Timestamp;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PostRowMapperTest {
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
        when(rs.getString("summary")).thenReturn("summary1");
        when(rs.getString("content")).thenReturn("<p>content</p>");
        when(rs.getTimestamp("creation_date")).thenReturn(now);
        when(rs.getTimestamp("last_update")).thenReturn(now);

        // When
        Post result = new PostRowMapper().mapRow(rs, 0);

        // Then
        Post expected = new Post(1, new Space(12), new User(11));
        expected.setDeleted(true);
        expected.setTitle("title1");
        expected.setSummary("summary1");
        expected.setContent("<p>content</p>");
        expected.setCreationDate(now);
        expected.setLastUpdate(now);
        assertThat(result, equalTo(expected));
    }
}