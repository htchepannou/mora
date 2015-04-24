package tchepannou.mora.insidesoccer.dao.impl;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.insidesoccer.dao.NodeDao;
import tchepannou.mora.insidesoccer.dao.mapper.NodeRowMapper;
import tchepannou.mora.insidesoccer.domain.Node;

public class NodeDaoImpl extends IsReadOnlyModelDao<Node> implements NodeDao {
    //-- Attributes
    private static final RowMapper<Node> MAPPER = new NodeRowMapper();

    //-- Node override

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
