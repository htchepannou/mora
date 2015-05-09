package tchepannou.mora.core.dao.jdbc;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.core.dao.EventDao;
import tchepannou.mora.core.dao.jdbc.mapper.EventRowMapper;
import tchepannou.mora.core.domain.Event;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

public class JdbcEventDao extends JdbcReadOnlyModelDao<Event> implements EventDao {
    private static final RowMapper<Event> MAPPER = new EventRowMapper();

    @Override
    protected RowMapper<Event> getRowMapper() {
        return MAPPER;
    }

    @Override
    protected String getTableName() {
        return "t_event";
    }

    @Override
    public List<Long> findIdsUpcomingForUser(long userId, int limit, int offset) {
        String sql = "SELECT DISTINCT E.id" +
                " FROM t_event E JOIN t_space S ON E.space_id=S.id" +
                "   JOIN t_member M ON M.space_id=S.id" +
                " WHERE M.user_id=?" +
                "   AND E.start_datetime>=?" +
                "   AND E.deleted=?" +
                " ORDER BY E.start_datetime" +
                " LIMIT " + limit + " OFFSET " + offset;

        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);

        return template.query(sql, new Object[] {userId, today, false}, new RowMapper<Long>() {
            @Override
            public Long mapRow(ResultSet rs, int i) throws SQLException {
                return rs.getLong("id");
            }
        });
    }
}
