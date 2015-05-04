package tchepannou.mora.insidesoccer.dao.impl;

import com.google.common.collect.Multimap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.core.dao.jdbc.mapper.JdbcDao;
import tchepannou.mora.core.domain.Model;
import tchepannou.mora.core.util.ComparatorById;
import tchepannou.mora.insidesoccer.dao.NodeAttributeDao;
import tchepannou.mora.insidesoccer.dao.NodePartyRelationshipDao;
import tchepannou.mora.insidesoccer.domain.NodeAttribute;
import tchepannou.mora.insidesoccer.domain.NodePartyRelationship;
import tchepannou.mora.insidesoccer.service.TeamResolver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public abstract class IsCompositeNodeDao<T extends Model> extends JdbcDao {
    //-- Attributes
    @Autowired
    private NodePartyRelationshipDao nodePartyRelationshipDao;

    @Autowired
    private NodeAttributeDao nodeAttributeDao;

    @Autowired
    private TeamResolver teamResolver;

    //-- Override
    protected abstract long getNodeTypeId ();

    protected abstract T convert(NodePartyRelationship node, Collection<NodeAttribute> attributes);

    protected abstract String[] getAttributeNames ();

    //-- EventDao overrides
    public T findById(long id) {
        NodePartyRelationship node = nodePartyRelationshipDao.findById(id);
        if (node == null || node.getTypeId() != getNodeTypeId()){
            return null;
        }

        List<NodeAttribute> attributes = nodeAttributeDao.findByNodePartyRelationshipByNames(id, getAttributeNames());

        return convert(node, attributes);
    }

    public List<T> findByIds(Collection<Long> ids) {
        List<NodePartyRelationship> nodes = nodePartyRelationshipDao.findByIds(ids);
        Multimap<Long, NodeAttribute> attributes = nodeAttributeDao.findByNodePartyRelationshipsByNames(ids, getAttributeNames());

        List<T> result = new LinkedList<>();
        for (NodePartyRelationship node : nodes){
            Collection<NodeAttribute> attrs = attributes.get(node.getId());
            T event = convert(node, attrs);
            result.add(event);
        }

        /* make sure the result is in the same order than the IDs */
        if (ids instanceof List){
            Collections.sort(result, new ComparatorById<>((List) ids));
        }
        return result;
    }

    public List<Long> findIdsPublishedForUser(long userId, int limit, int offset) {
        Set<Long> teamIds = teamResolver.getTeamIdsForUser(userId);
        if (teamIds.isEmpty()){
            return Collections.emptyList();
        } else {
            return findIdsPublishedForUserSince(userId, null, limit, offset);
        }
    }

    public List<Long> findIdsPublishedForUserSince(long userId, Date since, int limit, int offset) {
        Set<Long> teamIds = teamResolver.getTeamIdsForUser(userId);
        if (teamIds.isEmpty()){
            return Collections.emptyList();
        } else {
            StringBuilder sql = new StringBuilder("SELECT * FROM nprel JOIN node ON nprel_node_fk=node_id WHERE nprel_type_fk=? AND node_deleted=? AND ");

            List params = new ArrayList<>();
            params.add(getNodeTypeId());
            params.add(false);

            if (since != null){
                sql.append(" node_date>? AND");
                params.add(since);
            }
            whereIn(sql, "nprel_party_fk", teamIds, params);

            sql.append(String.format(" ORDER BY nprel_rank DESC, nprel_id DESC LIMIT %d OFFSET %d", limit, offset));

            List<NodeRelationshipEntry> entries = template.query(sql.toString(), params.toArray(), new RowMapper<NodeRelationshipEntry>() {
                @Override
                public NodeRelationshipEntry mapRow(ResultSet rs, int i) throws SQLException {
                    return new NodeRelationshipEntry(rs.getLong("nprel_id"), rs.getLong("nprel_node_fk"));
                }
            });

            /* filter the nodes */
            Set<Long> visited = new HashSet<>();
            List<Long> result = new ArrayList<>();
            for (NodeRelationshipEntry entry : entries) {
                if (visited.add(entry.getNodeId())) {
                    result.add(entry.getRelationshipId());
                }
            }
            return result;
        }
    }
}
