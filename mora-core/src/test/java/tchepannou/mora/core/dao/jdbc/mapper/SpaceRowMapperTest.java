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
        when(rs.getLong("type_id")).thenReturn(100L);
        when(rs.getString("name")).thenReturn("yo");
        when(rs.getString("description")).thenReturn("This is a description");
        when(rs.getString("logo_url")).thenReturn("http://img.com/1.png");
        when(rs.getBoolean("deleted")).thenReturn(true);
        when(rs.getTimestamp("creation_date")).thenReturn(now);
        when(rs.getTimestamp("last_update")).thenReturn(now);

        // When
        Space space = new SpaceRowMapper().mapRow(rs, 0);

        // Then
        Space expected = new Space(1, new SpaceType(100, "club"), new User(10));
        expected.setName("yo");
        expected.setDescription("This is a description");
        expected.setLogoUrl("http://img.com/1.png");
        expected.setDeleted(true);
        expected.setCreationDate(now);
        expected.setLastUpdate(now);

        assertThat (space, equalTo(expected));
    }
}