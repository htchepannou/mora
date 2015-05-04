package tchepannou.mora.core.dao.jdbc;

import tchepannou.mora.core.dao.EventTypeDao;
import tchepannou.mora.core.domain.EventType;

public class JdbcEventTypeDao extends JdbcEnumModelDao<EventType> implements EventTypeDao{
    //-- Constructor
    public JdbcEventTypeDao() {
        super(EventType.class);
    }

    //-- JdbcModelDao overrides
    @Override
    protected String getTableName() {
        return "t_event_type";
    }
}
