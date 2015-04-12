package tchepannou.mora.core.dao.jdbc.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tchepannou.mora.core.domain.Password;

import java.sql.ResultSet;
import java.sql.Timestamp;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PasswordRowMapperTest {
    @Mock
    private ResultSet rs;

    @Test
    public void testMapRow() throws Exception {
        // Given
        Timestamp now = new Timestamp(System.currentTimeMillis());

        when(rs.getLong("user_id")).thenReturn(1L);
        when(rs.getLong("id")).thenReturn(1L);
        when(rs.getString("value")).thenReturn("_secret_");
        when(rs.getTimestamp("creation_date")).thenReturn(now);
        when(rs.getTimestamp("last_update")).thenReturn(now);

        // When
        Password password = new PasswordRowMapper().mapRow(rs, 0);

        // Then
        Password expected = new Password();
        expected.setCreationDate(now);
        expected.setUserId(1);
        expected.setValue("_secret_");
        expected.setLastUpdate(now);
        expected.setId(1);
        assertThat (password, equalTo(expected));
    }
}