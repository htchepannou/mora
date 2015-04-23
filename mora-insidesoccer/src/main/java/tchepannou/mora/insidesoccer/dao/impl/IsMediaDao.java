package tchepannou.mora.insidesoccer.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import tchepannou.mora.core.dao.MediaDao;
import tchepannou.mora.core.domain.Media;
import tchepannou.mora.insidesoccer.dao.NodeAttributeDao;
import tchepannou.mora.insidesoccer.dao.NodeDao;
import tchepannou.mora.insidesoccer.domain.Node;
import tchepannou.mora.insidesoccer.domain.NodeAttribute;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class IsMediaDao implements MediaDao{
    //-- Attributes
    public static final long NODE_TYPE_ID = 100L;
    public static final long REL_TYPE_ID = 100L;
    public static final long MEMBER_TYPE_ID = 10L;
    public static final long PARTY_TEAM_ID=3;

    @Autowired
    private NodeDao nodeDao;

    @Autowired
    private NodeAttributeDao nodeAttributeDao;

    //-- MediaDao overrides
    @Override
    public Media findById(long id) {
        Node node = nodeDao.findById(id);
        if (node == null || node.getTypeId() != NODE_TYPE_ID){
            return null;
        }

        List<NodeAttribute> attributes = nodeAttributeDao.findByNodeByNames(id, NodeAttribute.MEDIA_ATTRIBUTES.toArray(new String[]{}));

        return toMedia(node, attributes);
    }

    @Override
    public List<Media> findByOwnerByAttachmentType(long ownerId, long typeId) {
        return Collections.emptyList();
    }

    //-- Private
    private Media toMedia (Node node, Collection<NodeAttribute> attributes){
        Media result = new Media ();
        node.toMedia(result);
        NodeAttribute.toMedia(attributes, result);

        return result;
    }

}
