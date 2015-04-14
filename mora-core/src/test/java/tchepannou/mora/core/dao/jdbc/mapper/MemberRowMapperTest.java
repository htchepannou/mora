package tchepannou.mora.core.dao.jdbc.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tchepannou.mora.core.domain.Member;

import java.sql.ResultSet;
import java.sql.Timestamp;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith (MockitoJUnitRunner.class)
public class MemberRowMapperTest {
    @Mock
    private ResultSet rs;

    @Test
    public void testMapRow() throws Exception {
        // Given
        Timestamp now = new Timestamp(System.currentTimeMillis());

        when(rs.getLong("user_id")).thenReturn(1L);
        when(rs.getLong("id")).thenReturn(1L);
        when(rs.getLong("user_id")).thenReturn(10L);
        when(rs.getLong("space_id")).thenReturn(100L);
        when(rs.getLong("role_id")).thenReturn(1000L);
        when(rs.getTimestamp("creation_date")).thenReturn(now);

        // When
        Member result = new MemberRowMapper().mapRow(rs, 0);

        // Then
        Member expected = new Member();
        expected.setCreationDate(now);
        expected.setUserId(1);
        expected.setUserId(10);
        expected.setSpaceId(100);
        expected.setRoleId(1000);
        expected.setCreationDate(now);
        expected.setId(1);
        assertThat (result, equalTo(expected));

    }
}