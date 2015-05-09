package tchepannou.mora.core.dao.jdbc.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tchepannou.mora.core.domain.Event;
import tchepannou.mora.core.domain.EventType;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.User;

import java.sql.ResultSet;
import java.sql.Timestamp;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith (MockitoJUnitRunner.class)
public class EventRowMapperTest {
    @Mock
    private ResultSet rs;

    @Test
    public void testMapRow() throws Exception {
        // Given
        Timestamp now = new Timestamp(System.currentTimeMillis());

        when(rs.getLong("id")).thenReturn(1L);
        when(rs.getLong("user_id")).thenReturn(11L);
        when(rs.getLong("space_id")).thenReturn(12L);
        when(rs.getLong("type_id")).thenReturn(3L);
        when(rs.getBoolean("deleted")).thenReturn(true);
        when(rs.getBoolean("require_rsvp")).thenReturn(true);
        when(rs.getString("title")).thenReturn("title1");
        when(rs.getString("notes")).thenReturn("<p>notes1</p>");
        when(rs.getString("location")).thenReturn("location1");
        when(rs.getString("url")).thenReturn("http://www.google.ca");
        when(rs.getTimestamp("start_datetime")).thenReturn(now);
        when(rs.getTimestamp("end_datetime")).thenReturn(now);
        when(rs.getTimestamp("creation_date")).thenReturn(now);
        when(rs.getTimestamp("last_update")).thenReturn(now);

        // When
        Event result = new EventRowMapper().mapRow(rs, 0);

        // Then
        Event expected = new Event(1, new EventType(3), new Space(12), new User(11));
        expected.setDeleted(true);
        expected.setTitle("title1");
        expected.setNotes("<p>notes1</p>");
        expected.setLocation("location1");
        expected.setUrl("http://www.google.ca");
        expected.setRequiresRSVP(true);
        expected.setStartDateTime(now);
        expected.setEndDateTime(now);
        expected.setCreationDate(now);
        expected.setLastUpdate(now);
        assertThat(result, equalTo(expected));

    }
}