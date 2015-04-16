package tchepannou.mora.insidesoccer.dao.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tchepannou.mora.core.domain.AccessToken;
import tchepannou.mora.core.domain.User;

import java.sql.ResultSet;
import java.sql.Timestamp;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith (MockitoJUnitRunner.class)
public class IsAccessTokenRowMapperTest {
    @Mock
    private ResultSet rs;


    @Test
    public void testMapRow() throws Exception {
        // Given
        Timestamp now = new Timestamp(System.currentTimeMillis());
        when(rs.getLong("login_id")).thenReturn(1L);
        when(rs.getBoolean("login_active")).thenReturn(true);
        when(rs.getTimestamp("login_date")).thenReturn(now);
        when(rs.getTimestamp("login_logout_date")).thenReturn(now);
        when(rs.getLong("login_party_fk")).thenReturn(10L);


        // When
        AccessToken result = new IsAccessTokenRowMapper().mapRow(rs, 0);

        // Then
        AccessToken expected = new AccessToken(1, new User(10));
        expected.setExpiryDate(now);
        expected.setCreationDate(now);
        expected.setValue("1");
        assertThat(result, equalTo(expected));
    }
}