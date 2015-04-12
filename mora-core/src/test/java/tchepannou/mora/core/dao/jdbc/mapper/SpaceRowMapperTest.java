package tchepannou.mora.core.dao.jdbc.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.SpaceType;
import tchepannou.mora.core.domain.User;

import java.sql.ResultSet;
import java.sql.Timestamp;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SpaceRowMapperTest {
    @Mock
    private ResultSet rs;


    @Test
    public void testMapRow() throws Exception {
        // Given
        Timestamp now = new Timestamp(System.currentTimeMillis());

        when(rs.getLong("id")).thenReturn(1L);
        when(rs.getLong("user_id")).thenReturn(10L);
        when(rs.getLong("space_type_id")).thenReturn(100L);
        when(rs.getString("name")).thenReturn("New York Soccer Club");
        when(rs.getString("abbreviation")).thenReturn("NYSC");
        when(rs.getString("description")).thenReturn("This is a description");
        when(rs.getString("logo_url")).thenReturn("http://img.com/1.png");
        when(rs.getString("website_url")).thenReturn("http://www.google.ca");
        when(rs.getString("email")).thenReturn("info@google.ca");
        when(rs.getString("address")).thenReturn("3030 Linton, Montreal, CA");
        when(rs.getBoolean("deleted")).thenReturn(true);
        when(rs.getTimestamp("creation_date")).thenReturn(now);
        when(rs.getTimestamp("last_update")).thenReturn(now);

        // When
        Space space = new SpaceRowMapper().mapRow(rs, 0);

        // Then
        Space expected = new Space(1, new SpaceType(100, "club"), new User(10));
        expected.setName("New York Soccer Club");
        expected.setAbbreviation("NYSC");
        expected.setDescription("This is a description");
        expected.setLogoUrl("http://img.com/1.png");
        expected.setWebsiteUrl("http://www.google.ca");
        expected.setEmail("info@google.ca");
        expected.setAddress("3030 Linton, Montreal, CA");
        expected.setDeleted(true);
        expected.setCreationDate(now);
        expected.setLastUpdate(now);

        assertThat (space, equalTo(expected));
    }
}