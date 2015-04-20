package tchepannou.mora.insidesoccer.dao.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.insidesoccer.dao.NodeAttributeDao;
import tchepannou.mora.insidesoccer.dao.mapper.NodeAttributeRowMapper;
import tchepannou.mora.insidesoccer.domain.NodeAttribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
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
        Multimap<Long, NodeAttribute> result = findByNodesByNames(Collections.singletonList(nodeId), names);
        return !result.isEmpty()
                ? new ArrayList(result.get(nodeId))
                : Collections.emptyList();
    }

    @Override
    public Multimap<Long, NodeAttribute> findByNodesByNames(Collection<Long> nodeIds, String... names) {
        StringBuilder sql = new StringBuilder("SELECT A.* FROM nattr A JOIN node P ON A.nattr_node_fk=P.node_id WHERE P.node_deleted=?");

        List params = new ArrayList();
        params.add(false);

        if (!nodeIds.isEmpty()) {
            sql.append(" AND ");
            whereIn(sql, "nattr_node_fk", nodeIds, params);
        }
        if (names.length>0){
            sql.append(" AND ");
            whereIn(sql, "nattr_name", Arrays.asList(names), params);
        }

        List<NodeAttribute> attributes = template.query(sql.toString(), params.toArray(), getRowMapper());

        Multimap<Long, NodeAttribute> result = ArrayListMultimap.create();
        for (NodeAttribute attr : attributes){
            result.put(attr.getNodeId(), attr);
        }
        return result;
    }
}
