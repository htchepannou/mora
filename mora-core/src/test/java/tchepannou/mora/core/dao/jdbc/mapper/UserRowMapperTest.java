package tchepannou.mora.core.dao.jdbc.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tchepannou.mora.core.domain.User;

import java.sql.ResultSet;
import java.sql.Timestamp;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith (MockitoJUnitRunner.class)
public class UserRowMapperTest {
    @Mock
    private ResultSet rs;

    @Test
    public void testMapRow() throws Exception {
        // Given
        Timestamp now = new Timestamp(System.currentTimeMillis());

        when(rs.getLong("id")).thenReturn(1L);
        when(rs.getString("username")).thenReturn("ray.sponsible");
        when(rs.getString("firstname")).thenReturn("Ray");
        when(rs.getString("lastname")).thenReturn("Sponsible");
        when(rs.getString("email")).thenReturn("ray.sponsible@gmail.com");
        when(rs.getBoolean("deleted")).thenReturn(true);
        when(rs.getTimestamp("creation_date")).thenReturn(now);
        when(rs.getTimestamp("last_update")).thenReturn(now);

        // When
        User user = new UserRowMapper().mapRow(rs, 0);

        // Then
        User expected = new User();
        expected.setId(1);
        expected.setUsername("ray.sponsible");
        expected.setFirstName("Ray");
        expected.setLastName("Sponsible");
        expected.setEmail("ray.sponsible@gmail.com");
        expected.setDeleted(true);
        expected.setCreationDate(now);
        expected.setLastUpdate(now);

        assertThat (user, equalTo(expected));

    }
}