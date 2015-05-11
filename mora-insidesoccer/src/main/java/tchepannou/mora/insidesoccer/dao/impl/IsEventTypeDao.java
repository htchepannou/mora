package tchepannou.mora.insidesoccer.dao.impl;

import tchepannou.mora.core.dao.EventTypeDao;
import tchepannou.mora.core.domain.EventType;
import tchepannou.mora.insidesoccer.domain.IsEventType;

import java.util.Arrays;
import java.util.List;

public class IsEventTypeDao extends IsMemoryEnumDao<EventType> implements EventTypeDao{

    private static final List<EventType> ALL = Arrays.asList(
            new EventType(IsEventType.MATCH, "match")
            ,new EventType(IsEventType.TRAINING, "training")
            ,new EventType(IsEventType.EVENT, "event")
            ,new EventType(IsEventType.TOURNAMENT, "tournament")
            ,new EventType(IsEventType.OTHER, "other")
    );

    @Override
    public List<EventType> findAll() {
        return ALL;
    }
}
