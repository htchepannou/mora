package tchepannou.mora.insidesoccer.dao.impl;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.insidesoccer.dao.NodeAttributeDao;
import tchepannou.mora.insidesoccer.dao.mapper.NodeAttributeRowMapper;
import tchepannou.mora.insidesoccer.domain.NodeAttribute;

import java.util.ArrayList;
import java.util.List;

public class NodeAttributeDaoImpl extends IsReadOnlyModelDao<NodeAttribute> implements NodeAttributeDao {
    //-- Attributes
    private static final RowMapper<NodeAttribute> MAPPER = new NodeAttributeRowMapper();

    //-- IsReadOnlyModelDao overrides
    @Override
    protected String getIdColumn() {
        return "nattr_id";
    }

    @Override
    protected String getTableName() {
        return "nattr";
    }

    @Override
    protected RowMapper<NodeAttribute> getRowMapper() {
        return MAPPER;
    }

    @Override
    public List<NodeAttribute> findByNodeByNames(long nodeId, String... names) {
        StringBuilder sql = new StringBuilder("SELECT A.* FROM nattr A JOIN node P ON A.nattr_node_fk=P.node_id WHERE A.nattr_node_fk=? AND P.node_deleted=?");

        List params = new ArrayList();
        params.add(nodeId);
        params.add(false);

        if (names.length>0) {
            sql.append(" AND nattr_name IN(");
            int i = 0;
            for (String name : names) {
                if (i++ > 0) {
                    sql.append(",");
                }
                sql.append("?");
                params.add(name);
            }
            sql.append(")");
        }
        return template.query(sql.toString(), params.toArray(), getRowMapper());
    }
}
