package tchepannou.mora.insidesoccer.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.insidesoccer.domain.NodeAttribute;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NodeAttributeRowMapper implements RowMapper<NodeAttribute> {
    @Override
    public NodeAttribute mapRow(ResultSet rs, int i) throws SQLException {
        NodeAttribute result = new NodeAttribute();
        result.setId(rs.getLong("nattr_id"));
        result.setNodeId(rs.getLong("nattr_node_fk"));
        result.setName(rs.getString("nattr_name"));
        result.setValue(rs.getString("nattr_value"));
        return result;
    }
}
