package tchepannou.mora.insidesoccer.domain;

import org.junit.Test;
import tchepannou.mora.core.domain.Event;
import tchepannou.mora.core.domain.Media;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class NodeTest {
    @Test
    public void testToMedia() throws Exception {
        // Given
        Date now = new Date();

        Node node = new Node(1);
        node.setDeleted(true);
        node.setTypeId(2);
        node.setOwnerId(3);
        node.setChannelId(1000);
        node.setStatus(4);
        node.setDate(now);

        // When
        Media result = new Media();
        node.toMedia(result);

        // Then
        Media expected = new Media(1);
        expected.setSpaceId(1000);
        expected.setUserId(3);
        expected.setDeleted(true);
        expected.setLastUpdate(now);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void testToEvent() throws Exception {
        // Given
        Date now = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-10-11 12:15");

        Node node = new Node(1);
        node.setDeleted(true);
        node.setTypeId(2);
        node.setOwnerId(3);
        node.setChannelId(1000);
        node.setStatus(4);
        node.setDate(now);

        // When
        Event result = new Event();
        node.toEvent(result);

        // Then
        Event expected = new Event(1);
        expected.setSpaceId(1000);
        expected.setUserId(3);
        expected.setDeleted(true);
        expected.setLastUpdate(now);
        expected.setStartDate(LocalDate.of(2015, 10, 11));
        assertThat(result, equalTo(expected));
    }
}