package tchepannou.mora.insidesoccer.dao.impl;

import tchepannou.mora.core.dao.EventDao;
import tchepannou.mora.core.domain.Event;
import tchepannou.mora.insidesoccer.domain.NodeAttribute;
import tchepannou.mora.insidesoccer.domain.NodePartyRelationship;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;

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

    //-- EventDao overrides
    @Override
    public List<Long> findIdsUpcomingForUser(long userId, int limit, int offset) {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);

        return findIdsPublishedForUserSince(userId, today.getTime(), true, limit, offset);
    }
}
