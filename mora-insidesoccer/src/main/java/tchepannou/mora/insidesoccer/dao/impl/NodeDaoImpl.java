package tchepannou.mora.insidesoccer.dao.impl;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.insidesoccer.dao.NodeDao;
import tchepannou.mora.insidesoccer.dao.mapper.NodeRowMapper;
import tchepannou.mora.insidesoccer.domain.Node;

import java.util.List;

public class NodeDaoImpl extends IsReadOnlyModelDao<Node> implements NodeDao {
    //-- Attributes
    private static final RowMapper<Node> MAPPER = new NodeRowMapper();

    //-- Node override
    @Override
    public List<Node> findInNodes(long nodeId, long nodeRelationshipTypeId) {
        String sql = "SELECT * FROM node"
                + "     JOIN nrel ON nrel_source_fk=node_id"
                + " WHERE nrel_dest_fk=? AND nrel_type_fk=? AND node_deleted=?"
                + " ORDER BY nrel_rank";
        return template.query(sql, new Object[]{nodeId, nodeRelationshipTypeId, false}, getRowMapper());
    }

    //-- IsReadOnlyModelDao overrides
    @Override
    protected String getIdColumn() {
        return "node_id";
    }

    @Override
    protected String getTableName() {
        return "node";
    }

    @Override
    protected RowMapper<Node> getRowMapper() {
        return MAPPER;
    }
}
