package tchepannou.mora.insidesoccer.dao.impl;

import tchepannou.mora.core.dao.PostDao;
import tchepannou.mora.core.domain.Post;
import tchepannou.mora.insidesoccer.domain.NodeAttribute;
import tchepannou.mora.insidesoccer.domain.NodePartyRelationship;

import java.util.Collection;

public class IsPostDao extends IsCompositeNodeDao<Post> implements PostDao{
    //-- IsCompositeNodeDao overrides

    @Override
    protected Post convert(NodePartyRelationship node, Collection<NodeAttribute> attributes) {
        Post result = new Post ();
        node.toPost(result);
        NodeAttribute.toPost(attributes, result);

        return result;
    }

    @Override
    protected long getNodeTypeId() {
        return NodePartyRelationship.TYPE_BLOG;
    }

    @Override
    protected String[] getAttributeNames() {
        return NodeAttribute.POST_ATTRIBUTES.toArray(new String[] {});
    }

    //-- PostDao overrides
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
}
