package tchepannou.mora.insidesoccer.dao.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tchepannou.mora.insidesoccer.domain.IsRole;

import java.sql.ResultSet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith (MockitoJUnitRunner.class)
public class IsRoleRowMapperTest {
    @Mock
    private ResultSet rs;

    @Test
    public void testMapRow() throws Exception {
        // Given
        when(rs.getLong("role_id")).thenReturn(1L);
        when(rs.getString("role_name")).thenReturn("foo");
        when(rs.getBoolean("role_access_all_teams")).thenReturn(true);

        // When
        IsRole result = new IsRoleRowMapper().mapRow(rs, 0);

        // Then
        IsRole expected = new IsRole(1, "foo", true);
        assertThat(result, equalTo(expected));
    }}