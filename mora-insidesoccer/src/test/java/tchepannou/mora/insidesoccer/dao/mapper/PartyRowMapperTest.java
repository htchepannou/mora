package tchepannou.mora.insidesoccer.dao.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tchepannou.mora.insidesoccer.domain.Party;

import java.sql.ResultSet;
import java.sql.Timestamp;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith (MockitoJUnitRunner.class)
public class PartyRowMapperTest {
    @Mock
    private ResultSet rs;

    @Test
    public void testMapRow() throws Exception {
        // Given
        Timestamp now = new Timestamp(System.currentTimeMillis());
        when(rs.getLong("party_id")).thenReturn(1L);
        when(rs.getLong("party_type_fk")).thenReturn(10L);
        when(rs.getLong("party_owner_fk")).thenReturn(100L);
        when(rs.getBoolean("party_deleted")).thenReturn(true);
        when(rs.getInt("party_status")).thenReturn(3);
        when(rs.getTimestamp("party_date")).thenReturn(now);
        when(rs.getTimestamp("party_creation_date")).thenReturn(now);
        
        // When
        Party result = new PartyRowMapper().mapRow(rs, 0);
        
        // Then
        Party expected = new Party();
        expected.setId(1L);
        expected.setTypeId(10);
        expected.setOwnerId(100);
        expected.setDeleted(true);
        expected.setStatus(3);
        expected.setDate(now);
        expected.setCreationDate(now);
        assertThat(result, equalTo(expected));
    }
}