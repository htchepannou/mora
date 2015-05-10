package tchepannou.mora.insidesoccer.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.insidesoccer.domain.NodePartyRelationship;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NodeRelationshipRowMapper implements RowMapper<NodePartyRelationship> {
    @Override
    public NodePartyRelationship mapRow(ResultSet rs, int i) throws SQLException {
        NodePartyRelationship result = new NodePartyRelationship();
        result.setId(rs.getLong("nprel_id"));
        result.setPrimary(rs.getBoolean("nprel_primary"));
        result.setRank(rs.getLong("nprel_rank"));
        result.setNodeId(rs.getLong("nprel_node_fk"));
        result.setPartyId(rs.getLong("nprel_party_fk"));
        result.setQualifier(rs.getString("nprel_qualifier"));

        result.setChannelId(rs.getLong("node_channel_fk"));
        result.setOwnerId(rs.getLong("node_owner_fk"));
        result.setTypeId(rs.getLong("node_type_fk"));
        result.setStatus(rs.getInt("node_status"));
        result.setDeleted(rs.getBoolean("node_deleted"));

        try{
            result.setDate(rs.getTimestamp("node_date"));   // Id node_date==0000-00-00 00:00:00
        } catch (SQLException e){
            result.setDate(rs.getDate("node_date"));
        }
        return result;
    }
}
