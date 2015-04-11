package tchepannou.mora.core.dao.jdbc.mapper;

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
public class SpaceTypeMapperTest {
    @Mock
    private ResultSet rs;


    @Test
    public void testMapRow() throws Exception {
        // Given
        when(rs.getLong("id")).thenReturn(1L);
        when(rs.getString("name")).thenReturn("foo");

        // When
        SpaceType result = new SpaceTypeMapper().mapRow(rs, 0);

        // Then
        SpaceType expected = new SpaceType(1, "foo");
        assertThat (result, equalTo(expected));
    }
}