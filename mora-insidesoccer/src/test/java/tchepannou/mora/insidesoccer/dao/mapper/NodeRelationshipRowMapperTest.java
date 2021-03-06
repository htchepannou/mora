package tchepannou.mora.insidesoccer.dao.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tchepannou.mora.insidesoccer.domain.NodePartyRelationship;

import java.sql.ResultSet;
import java.sql.Timestamp;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith (MockitoJUnitRunner.class)
public class NodeRelationshipRowMapperTest {
    @Mock
    private ResultSet rs;

    @Test
    public void testMapRow() throws Exception {
        // Given
        Timestamp now = new Timestamp(System.currentTimeMillis());
        when(rs.getLong("nprel_id")).thenReturn(1L);
        when(rs.getLong("nprel_party_fk")).thenReturn(1000L);
        when(rs.getLong("nprel_node_fk")).thenReturn(2000L);
        when(rs.getLong("nprel_rank")).thenReturn(3000L);
        when(rs.getBoolean("nprel_primary")).thenReturn(true);
        when(rs.getString("nprel_qualifier")).thenReturn("foo");

        when(rs.getLong("node_channel_fk")).thenReturn(4000L);
        when(rs.getLong("node_type_fk")).thenReturn(10L);
        when(rs.getLong("node_owner_fk")).thenReturn(100L);
        when(rs.getBoolean("node_deleted")).thenReturn(true);
        when(rs.getInt("node_status")).thenReturn(3);
        when(rs.getTimestamp("node_date")).thenReturn(now);

        // When
        NodePartyRelationship result = new NodeRelationshipRowMapper().mapRow(rs, 0);
        
        // Then
        NodePartyRelationship expected = new NodePartyRelationship();
        expected.setId(1L);
        expected.setPartyId(1000);
        expected.setNodeId(2000);
        expected.setRank(3000);
        expected.setPrimary(true);
        expected.setQualifier("foo");
        expected.setTypeId(10);
        expected.setOwnerId(100);
        expected.setChannelId(4000);
        expected.setDeleted(true);
        expected.setStatus(3);
        expected.setDate(now);
        assertThat(result, equalTo(expected));
    }
}