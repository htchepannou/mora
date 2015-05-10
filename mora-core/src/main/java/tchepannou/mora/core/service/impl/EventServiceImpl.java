package tchepannou.mora.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import tchepannou.mora.core.dao.EventDao;
import tchepannou.mora.core.domain.Event;
import tchepannou.mora.core.service.EventService;

import java.util.Collection;
import java.util.List;

public class EventServiceImpl implements EventService {
    //-- Attributes
    //-- Attributes
    private static final Logger LOG = LoggerFactory.getLogger(EventServiceImpl.class);

    @Autowired
    private EventDao dao;

    //-- EventService overrides
    @Override
    @Cacheable ("Event")
    public Event findById(long id) {
        LOG.debug("findById({})", id);

        return dao.findById (id);
    }

    @Override
    public List<Event> findByIds(Collection<Long> ids) {
        LOG.debug("findByIds({})", ids);

        return dao.findByIds(ids);
    }

    @Override
    public List<Long> findIdsUpcomingForUser(long userId, int limit, int offset) {
        LOG.debug("findIdsUpcomingForUser({}, {}, {})", userId, limit, offset);

        return dao.findIdsUpcomingForUser(userId, limit, offset);
    }
}
