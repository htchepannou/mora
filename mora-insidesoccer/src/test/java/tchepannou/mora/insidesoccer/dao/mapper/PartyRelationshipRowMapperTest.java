package tchepannou.mora.insidesoccer.dao.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tchepannou.mora.insidesoccer.domain.PartyRelationship;

import java.sql.ResultSet;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PartyRelationshipRowMapperTest {
    @Mock
    private ResultSet rs;

    @Test
    public void testMapRow() throws Exception {
        // Given
        when(rs.getLong("prel_id")).thenReturn(1L);
        when(rs.getLong("prel_type_fk")).thenReturn(2L);
        when(rs.getLong("prel_source_fk")).thenReturn(3L);
        when(rs.getLong("prel_dest_fk")).thenReturn(4L);
        when(rs.getLong("prel_rank")).thenReturn(5L);
        when(rs.getString("prel_qualifier")).thenReturn("foo");

        // When
        PartyRelationship result = new PartyRelationshipRowMapper().mapRow(rs, 0);

        // Then
        PartyRelationship expected = new PartyRelationship();
        expected.setId(1);
        expected.setTypeId(2);
        expected.setSourceId(3);
        expected.setDestinationId(4);
        expected.setRank(5);
        expected.setQualifier("foo");
        assertThat(result, equalTo(expected));
    }
}