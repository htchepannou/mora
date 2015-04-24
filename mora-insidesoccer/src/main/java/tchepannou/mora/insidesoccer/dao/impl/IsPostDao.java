package tchepannou.mora.insidesoccer.dao.impl;

import com.google.common.collect.Multimap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.core.dao.PostDao;
import tchepannou.mora.core.domain.Post;
import tchepannou.mora.insidesoccer.dao.NodeAttributeDao;
import tchepannou.mora.insidesoccer.dao.NodeDao;
import tchepannou.mora.insidesoccer.domain.Node;
import tchepannou.mora.insidesoccer.domain.NodeAttribute;
import tchepannou.mora.insidesoccer.service.TeamResolver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class IsPostDao extends IsReadOnlyModelDao<Post> implements PostDao{
    //-- Attributes
    private static final Logger LOG = LoggerFactory.getLogger(IsPostDao.class);

    @Autowired
    private NodeDao nodeDao;

    @Autowired
    private NodeAttributeDao nodeAttributeDao;

    @Autowired
    private TeamResolver teamResolver;


    //-- IsReadOnlyModelDao overrides
    @Override
    protected String getIdColumn() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected String getTableName() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected RowMapper<Post> getRowMapper() {
        throw new UnsupportedOperationException();
    }

    //-- PostDao overrides
    @Override
    public Post findById(long id) {
        Node node = nodeDao.findById(id);
        if (node == null || node.getTypeId() != Node.TYPE_BLOG){
            return null;
        }

        List<NodeAttribute> attributes = nodeAttributeDao.findByNodeByNames(id, NodeAttribute.POST_ATTRIBUTES.toArray(new String[]{}));

        return toPost(node, attributes);
    }

    @Override
    public List<Post> findByIds (Collection<Long> ids) {
        List<Node> nodes = nodeDao.findByIds(ids);
        Multimap<Long, NodeAttribute> attributes = nodeAttributeDao.findByNodesByNames(ids, NodeAttribute.POST_ATTRIBUTES.toArray(new String[]{}));

        List<Post> result = new LinkedList<>();
        for (Node node : nodes){
            Collection<NodeAttribute> attrs = attributes.get(node.getId());
            Post post = toPost(node, attrs);
            result.add(post);
        }
        return result;
    }

    @Override
    public List<Long> findIdsPublishedForUser(long userId, int limit, int offset) {
        Set<Long> teamIds = teamResolver.getTeamIdsForUser(userId);
        if (teamIds.isEmpty()){
            return Collections.emptyList();
        } else {
            LOG.info("Teams of " + userId + ": " + teamIds);

            StringBuilder sql = new StringBuilder("SELECT * FROM nprel JOIN node ON nprel_node_fk=node_id WHERE nprel_type_fk=? AND node_deleted=? AND ");

            List params = new ArrayList<>();
            params.add(Node.RELATION_BLOG);
            params.add(false);
            whereIn(sql, "nprel_party_fk", teamIds, params);

            sql.append(String.format(" ORDER BY nprel_id DESC LIMIT %d OFFSET %d", limit, offset));

            List<Entry> entries = template.query(sql.toString(), params.toArray(), new RowMapper<Entry>() {
                @Override
                public Entry mapRow(ResultSet rs, int i) throws SQLException {
                    return new Entry(rs.getLong("nprel_id"), rs.getLong("nprel_node_fk"));
                }
            });

            /* filter the nodes */
            Set<Long> visited = new HashSet<>();
            List<Long> result = new ArrayList<>();
            for (Entry entry : entries) {
                if (visited.add(entry.getNodeId())) {
                    result.add(entry.getRelationshipId());
                }
            }
            return result;
        }
    }

    //-- Private
    private Post toPost (Node node, Collection<NodeAttribute> attributes){
        Post result = new Post ();
        node.toPost(result);
        NodeAttribute.toPost(attributes, result);

        return result;
    }

    //-- Inner class
    private static class Entry{
        private long relationshipId;
        private long nodeId;

        //-- Constructor
        public Entry(long relationshipId, long nodeId) {
            this.nodeId = nodeId;
            this.relationshipId = relationshipId;
        }

        //-- Getter
        public long getNodeId() {
            return nodeId;
        }

        public long getRelationshipId() {
            return relationshipId;
        }
    }
}
