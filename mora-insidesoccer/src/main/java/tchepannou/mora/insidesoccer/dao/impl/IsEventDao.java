package tchepannou.mora.insidesoccer.dao.impl;

import tchepannou.mora.core.dao.EventDao;
import tchepannou.mora.core.domain.Event;
import tchepannou.mora.insidesoccer.domain.NodeAttribute;
import tchepannou.mora.insidesoccer.domain.NodePartyRelationship;

import java.util.Collection;

public class IsEventDao extends IsCompositeNodeDao<Event> implements EventDao {
    @Override
    protected Event convert(NodePartyRelationship node, Collection<NodeAttribute> attributes) {
        Event result = new Event ();
        node.toEvent(result);
        NodeAttribute.toEvent(attributes, result);

        return result;
    }

    @Override
    protected long getNodeTypeId() {
        return NodePartyRelationship.TYPE_EVENT;
    }

    @Override
    protected String[] getAttributeNames() {
        return NodeAttribute.EVENT_ATTRIBUTES.toArray(new String[] {});
    }
}
