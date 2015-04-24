package tchepannou.mora.insidesoccer.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import tchepannou.mora.core.dao.MediaDao;
import tchepannou.mora.core.domain.Media;
import tchepannou.mora.insidesoccer.dao.NodeAttributeDao;
import tchepannou.mora.insidesoccer.dao.NodePartyRelationshipDao;
import tchepannou.mora.insidesoccer.domain.NodePartyRelationship;
import tchepannou.mora.insidesoccer.domain.NodeAttribute;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class IsMediaDao implements MediaDao{
    //-- Attributes
    @Autowired
    private NodePartyRelationshipDao nodePartyRelationshipDao;

    @Autowired
    private NodeAttributeDao nodeAttributeDao;

    //-- MediaDao overrides
    @Override
    public Media findById(long id) {
        NodePartyRelationship node = nodePartyRelationshipDao.findById(id);
        if (node == null || node.getTypeId() != NodePartyRelationship.TYPE_BLOG){
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
    private Media toMedia (NodePartyRelationship node, Collection<NodeAttribute> attributes){
        Media result = new Media ();
        node.toMedia(result);
        NodeAttribute.toMedia(attributes, result);

        return result;
    }
}
