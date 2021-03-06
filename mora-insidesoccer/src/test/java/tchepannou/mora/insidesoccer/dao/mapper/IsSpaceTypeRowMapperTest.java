package tchepannou.mora.insidesoccer.dao.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tchepannou.mora.core.domain.SpaceType;

import java.sql.ResultSet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith (MockitoJUnitRunner.class)
public class IsSpaceTypeRowMapperTest {
    @Mock
    private ResultSet rs;

    @Test
    public void testMapRow() throws Exception {
        // Given
        when(rs.getLong("ptype_id")).thenReturn(1L);
        when(rs.getString("ptype_name")).thenReturn("foo");

        // When
        SpaceType result = new IsSpaceTypeRowMapper().mapRow(rs, 0);

        // Then
        SpaceType expected = new SpaceType(1, "foo");
        assertThat (result, equalTo(expected));

    }}