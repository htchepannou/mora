package tchepannou.mora.insidesoccer.dao.impl;

import com.google.common.collect.Multimap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.core.dao.PostDao;
import tchepannou.mora.core.domain.Post;
import tchepannou.mora.insidesoccer.dao.NodeAttributeDao;
import tchepannou.mora.insidesoccer.dao.NodeDao;
import tchepannou.mora.insidesoccer.domain.Node;
import tchepannou.mora.insidesoccer.domain.NodeAttribute;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class IsPostDao extends IsReadOnlyModelDao<Post> implements PostDao{
    //-- Attributes
    public static final long NODE_TYPE_ID = 1L;
    public static final long REL_TYPE_ID = 1L;
    public static final long MEMBER_TYPE_ID = 10L;

    @Autowired
    private NodeDao nodeDao;

    @Autowired
    private NodeAttributeDao nodeAttributeDao;


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
        if (node == null || node.getTypeId() != NODE_TYPE_ID){
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
        String sql = "SELECT DISTINCT R.nprel_node_fk FROM nprel R\n" +
                " JOIN node N  ON R.nprel_node_fk=N.node_id\n" +
                " JOIN party P ON R.nprel_party_fk=P.party_id\n" +
                " JOIN prel PR ON R.nprel_party_fk=PR.prel_dest_fk\n" +
                " WHERE N.node_type_fk=? AND R.nprel_type_fk=? AND N.node_deleted=? AND P.party_deleted=? AND PR.prel_type_fk=? AND PR.prel_source_fk=?\n" +
                " ORDER BY N.node_date DESC\n" +
                " LIMIT " + limit + " OFFSET " + offset;

        Object[] params = new Object[]{
                NODE_TYPE_ID,
                REL_TYPE_ID,
                false,
                false,
                MEMBER_TYPE_ID,
                userId
        };
        return template.query(sql, params, new RowMapper<Long>() {
            @Override
            public Long mapRow(ResultSet rs, int i) throws SQLException {
                return rs.getLong(1);
            }
        });
    }

    @Override
    public long create(Post post) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Post post) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Post post) {
        throw new UnsupportedOperationException();
    }

    //-- Private
    private Post toPost (Node node, Collection<NodeAttribute> attributes){
        Post result = new Post ();
        node.toPost(result);
        NodeAttribute.toPost(attributes, result);
        return result;
    }
}
