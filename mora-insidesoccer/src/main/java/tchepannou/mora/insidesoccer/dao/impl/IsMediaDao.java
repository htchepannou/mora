package tchepannou.mora.insidesoccer.dao.impl;

import com.google.common.base.Strings;
import com.google.common.collect.Multimap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import tchepannou.mora.core.dao.MediaDao;
import tchepannou.mora.core.dao.jdbc.mapper.JdbcDao;
import tchepannou.mora.core.domain.Media;
import tchepannou.mora.core.domain.Model;
import tchepannou.mora.core.util.ComparatorById;
import tchepannou.mora.insidesoccer.dao.NodeAttributeDao;
import tchepannou.mora.insidesoccer.dao.NodeDao;
import tchepannou.mora.insidesoccer.dao.NodePartyRelationshipDao;
import tchepannou.mora.insidesoccer.domain.Node;
import tchepannou.mora.insidesoccer.domain.NodeAttribute;
import tchepannou.mora.insidesoccer.domain.NodePartyRelationship;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class IsMediaDao extends JdbcDao implements MediaDao{
    //-- Attributes
    @Autowired
    private NodeDao nodeDao;

    @Autowired
    private NodePartyRelationshipDao nodePartyRelationshipDao;

    @Autowired
    private NodeAttributeDao nodeAttributeDao;

    @Value ("${insidesoccer.asset.url}")
    private String assetUrl;


    //-- MediaDao overrides
    @Override
    public Media findById(long nodeId) {
        Node node = nodeDao.findById(nodeId);
        if (node == null || node.getTypeId() != NodePartyRelationship.TYPE_ATTACHMENT){
            return null;
        }

        List<NodeAttribute> attributes = nodeAttributeDao.findByNodeByNames(nodeId, NodeAttribute.MEDIA_ATTRIBUTES.toArray(new String[]{}));

        return toMedia(node, attributes);
    }

    @Override
    public List<Media> findByOwnerByAttachmentType(long nodePartyRelationshipId, long typeId) {
        /* get the attachment nodes */
        NodePartyRelationship rel = nodePartyRelationshipDao.findById(nodePartyRelationshipId);
        if (rel == null){
            return Collections.emptyList();
        }

        List<Node> nodes = nodeDao.findInNodes(rel.getNodeId(), Node.TYPE_ATTACHMENT);
        List<Long> nodeIds = Model.idList(nodes);

        /* Get the attributes */
        Multimap<Long, NodeAttribute> attributes = nodeAttributeDao.findByNodesByNames(nodeIds, NodeAttribute.MEDIA_ATTRIBUTES.toArray(new String[]{}));

        return nodes.stream()
                .map(f -> toMedia(f, attributes.get(f.getId())))
                .sorted(new ComparatorById<>(nodeIds))
                .collect(Collectors.toList());

    }

    //-- Private
    private Media toMedia (Node node, Collection<NodeAttribute> attributes){
        Media result = new Media ();
        node.toMedia(result);
        NodeAttribute.toMedia(attributes, result);

        result.setUrl(prefixUrl(result.getUrl()));
        result.setImageUrl(prefixUrl(result.getImageUrl()));
        result.setThumbnailUrl(prefixUrl(result.getThumbnailUrl()));

        return result;
    }

    private String prefixUrl(String url){
        if (!Strings.isNullOrEmpty(url) && !url.startsWith("http://") && !url.startsWith("https://")) {
            return assetUrl + url;
        }
        return url;
    }
}
