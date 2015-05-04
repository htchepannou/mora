package tchepannou.mora.core.dao.jdbc;

import tchepannou.mora.core.dao.EventDao;
import tchepannou.mora.core.domain.Event;

import java.util.Collection;
import java.util.List;

public class JdbcEventDao implements EventDao {
    @Override
    public Event findById(long id) {
        return null;
    }

    @Override
    public List<Event> findByIds(Collection<Long> ids) {
        return null;
    }

    @Override
    public List<Long> findIdsUpcomingForUser(long userId, int limit, int offset) {
        return null;
    }
}
