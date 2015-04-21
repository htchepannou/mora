package tchepannou.mora.insidesoccer.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.insidesoccer.domain.Node;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NodeRowMapper  implements RowMapper<Node> {
    @Override
    public Node mapRow(ResultSet rs, int i) throws SQLException {
        Node result = new Node();
        result.setId(rs.getLong("nprel_id"));
        result.setOwnerId(rs.getLong("node_owner_fk"));
        result.setChannelId(rs.getLong("nprel_party_fk"));
        result.setTypeId(rs.getLong("node_type_fk"));
        result.setStatus(rs.getInt("node_status"));
        result.setDeleted(rs.getBoolean("node_deleted"));
        result.setDate(rs.getTimestamp("node_date"));
        return result;
    }
}
