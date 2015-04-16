package tchepannou.mora.insidesoccer.dao.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tchepannou.mora.insidesoccer.domain.PartyAttribute;

import java.sql.ResultSet;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith (MockitoJUnitRunner.class)
public class PartyAttributeRowMapperTest {
    @Mock
    private ResultSet rs;

    @Test
    public void testMapRow() throws Exception {
        // Given
        when(rs.getLong("pattr_id")).thenReturn(1L);
        when(rs.getLong("pattr_party_fk")).thenReturn(11L);
        when(rs.getString("pattr_name")).thenReturn("title");
        when(rs.getString("pattr_value")).thenReturn("Hello world");

        // When
        PartyAttribute result = new PartyAttributeRowMapper().mapRow(rs, 0);

        // Then
        PartyAttribute expected = new PartyAttribute();
        expected.setId(1);
        expected.setPartyId(11);
        expected.setName("title");
        expected.setValue("Hello world");
        assertThat(result, equalTo(expected));
    }
}