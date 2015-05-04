package tchepannou.mora.core.dao;

import tchepannou.mora.core.domain.Event;

import java.util.Collection;
import java.util.List;

public interface EventDao {
    Event findById(long id);
    List<Event> findByIds(Collection<Long> ids);
    List<Long> findIdsPublishedForUser(long userId, int limit, int offset);
}
