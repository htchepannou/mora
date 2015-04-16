package tchepannou.mora.insidesoccer.dao.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tchepannou.mora.core.domain.Role;

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

        // When
        Role result = new IsRoleRowMapper().mapRow(rs, 0);

        // Then
        Role expected = new Role(1, "foo");
        assertThat (result, equalTo(expected));

    }}