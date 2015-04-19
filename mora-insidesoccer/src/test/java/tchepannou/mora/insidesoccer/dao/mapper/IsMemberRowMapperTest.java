package tchepannou.mora.insidesoccer.dao.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tchepannou.mora.core.domain.Member;
import tchepannou.mora.core.domain.Role;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.SpaceType;
import tchepannou.mora.core.domain.User;

import java.sql.ResultSet;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith (MockitoJUnitRunner.class)
public class IsMemberRowMapperTest {
    @Mock
    private ResultSet rs;

    @Test
    public void testMapRow() throws Exception {
        // Given
        when (rs.getLong("prel_id")).thenReturn(1L);
        when (rs.getLong("prel_source_fk")).thenReturn(10L);
        when (rs.getLong("prel_dest_fk")).thenReturn(11L);
        when (rs.getString("prel_qualifier")).thenReturn("20");

        // When
        Member result = new IsMemberRowMapper().mapRow(rs, 0);

        // Then
        Member expected = new Member(1, new Space(11, new SpaceType(1), new User(1)), new User(10), new Role(20));
        assertThat(result, equalTo(expected));
    }
}