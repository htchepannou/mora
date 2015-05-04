package tchepannou.mora.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import tchepannou.mora.core.dao.EventDao;
import tchepannou.mora.core.domain.Event;
import tchepannou.mora.core.service.EventService;

import java.util.Collection;
import java.util.List;

public class EventServiceImpl implements EventService {
    //-- Attributes
    @Autowired
    private EventDao dao;

    //-- EventService overrides
    @Override
    public Event findById(long id) {
        return dao.findById (id);
    }

    @Override
    public List<Event> findByIds(Collection<Long> ids) {
        return dao.findByIds(ids);
    }

    @Override
    public List<Long> findIdsUpcomingForUser(long userId, int limit, int offset) {
        return dao.findIdsUpcomingForUser(userId, limit, offset);
    }
}
