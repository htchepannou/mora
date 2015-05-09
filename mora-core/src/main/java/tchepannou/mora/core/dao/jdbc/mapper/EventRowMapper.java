package tchepannou.mora.core.dao.jdbc.mapper;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.core.domain.Event;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventRowMapper implements RowMapper<Event>{
    @Override
    public Event mapRow(ResultSet rs, int i) throws SQLException {
        Event result = new Event();
        result.setId(rs.getLong("id"));
        result.setUserId(rs.getLong("user_id"));
        result.setSpaceId(rs.getLong("space_id"));
        result.setTypeId(rs.getLong("type_id"));
        result.setDeleted(rs.getBoolean("deleted"));
        result.setTitle(rs.getString("title"));
        result.setNotes(rs.getString("notes"));
        result.setLocation(rs.getString("location"));
        result.setUrl(rs.getString("url"));
        result.setRequiresRSVP(rs.getBoolean("require_rsvp"));
        result.setStartDateTime(rs.getTimestamp("start_datetime"));
        result.setEndDateTime(rs.getTimestamp("end_datetime"));
        result.setCreationDate(rs.getTimestamp("creation_date"));
        result.setLastUpdate(rs.getTimestamp("last_update"));
        return result;
    }
}
