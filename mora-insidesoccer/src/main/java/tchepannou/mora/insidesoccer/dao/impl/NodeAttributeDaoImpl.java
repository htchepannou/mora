package tchepannou.mora.insidesoccer.dao.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.core.dao.jdbc.mapper.JdbcDao;
import tchepannou.mora.insidesoccer.dao.NodeAttributeDao;
import tchepannou.mora.insidesoccer.dao.mapper.NodeAttributeRowMapper;
import tchepannou.mora.insidesoccer.domain.NodeAttribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class NodeAttributeDaoImpl extends JdbcDao implements NodeAttributeDao {
    //-- Attributes
    private static final RowMapper<NodeAttribute> MAPPER = new NodeAttributeRowMapper();

    //-- NodeAttributeDao overrides
    @Override
    public List<NodeAttribute> findByNodePartyRelationshipByNames(long nodePartyRelationshipId, String... names) {
        Multimap<Long, NodeAttribute> result = findByNodePartyRelationshipsByNames(Collections.singletonList(nodePartyRelationshipId), names);
        return !result.isEmpty()
                ? new ArrayList(result.get(nodePartyRelationshipId))
                : Collections.emptyList();
    }

    @Override
    public Multimap<Long, NodeAttribute> findByNodePartyRelationshipsByNames(Collection<Long> nodePartyRelationshipId, String... names) {
        if (nodePartyRelationshipId.isEmpty()){
            return ArrayListMultimap.create();
        }

        StringBuilder sql = new StringBuilder("SELECT R.nprel_id AS node_id, A.*" +
                " FROM nattr A" +
                "   JOIN node N ON A.nattr_node_fk=N.node_id" +
                "   JOIN nprel R ON A.nattr_node_fk=R.nprel_node_fk" +
                " WHERE N.node_deleted=?");

        List params = new ArrayList();
        params.add(false);


        whereAnd(sql);
        whereIn(sql, "R.nprel_id", nodePartyRelationshipId, params);

        if (names.length>0){
            whereAnd(sql);
            whereIn(sql, "nattr_name", Arrays.asList(names), params);
        }

        List<NodeAttribute> attributes = template.query(sql.toString(), params.toArray(), MAPPER);

        Multimap<Long, NodeAttribute> result = ArrayListMultimap.create();
        for (NodeAttribute attr : attributes){
            result.put(attr.getNodeId(), attr);
        }
        return result;
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
        StringBuilder sql = new StringBuilder("SELECT A.*, A.nattr_node_fk AS node_id" +
                " FROM nattr A" +
                "   JOIN node N ON A.nattr_node_fk=N.node_id" +
                " WHERE N.node_deleted=?");

        List params = new ArrayList();
        params.add(false);


        if (!nodeIds.isEmpty()) {
            whereAnd(sql);
            whereIn(sql, "A.nattr_node_fk", nodeIds, params);
        }
        if (names.length>0){
            whereAnd(sql);
            whereIn(sql, "nattr_name", Arrays.asList(names), params);
        }

        List<NodeAttribute> attributes = template.query(sql.toString(), params.toArray(), MAPPER);

        Multimap<Long, NodeAttribute> result = ArrayListMultimap.create();
        for (NodeAttribute attr : attributes){
            result.put(attr.getNodeId(), attr);
        }
        return result;
    }
}
