package tchepannou.mora.core.service;

import tchepannou.mora.core.domain.Event;

import java.util.Collection;
import java.util.List;

public interface EventService {
    Event findById(long id);
    List<Event> findByIds(Collection<Long> ids);
    List<Long> findIdsUpcomingForUser(long userId, int limit, int offset);
}
