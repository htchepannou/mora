package tchepannou.mora.insidesoccer.domain;

import org.junit.Test;
import tchepannou.mora.core.domain.Event;
import tchepannou.mora.core.domain.Post;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class NodePartyRelationshipTest {

    @Test
    public void testToPost() throws Exception {
        // Given
        Date now = new Date();

        NodePartyRelationship node = new NodePartyRelationship(1);
        node.setDeleted(true);
        node.setTypeId(2);
        node.setOwnerId(3);
        node.setChannelId(1000);
        node.setStatus(4);
        node.setDate(now);
        node.setRank(1429882200);
        node.setQualifier("foo");
        node.setPrimary(true);

        // When
        Post result = new Post();
        node.toPost(result);

        // Then
        Post expected = new Post(1);
        expected.setSpaceId(1000);
        expected.setUserId(3);
        expected.setDeleted(true);
        expected.setLastUpdate(new Timestamp(1429882200));
        assertThat(result, equalTo(expected));

    }


    @Test
    public void testToEvent() throws Exception {
        // Given
        Date now = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-10-11 12:15");

        NodePartyRelationship node = new NodePartyRelationship(1);
        node.setDeleted(true);
        node.setTypeId(4);
        node.setOwnerId(3);
        node.setChannelId(1000);
        node.setStatus(4);
        node.setDate(now);
        node.setRank(1429882200);
        node.setQualifier("foo");
        node.setPrimary(true);

        // When
        Event result = new Event();
        node.toEvent(result);

        // Then
        Event expected = new Event(1);
        expected.setSpaceId(1000);
        expected.setUserId(3);
        expected.setDeleted(true);
        expected.setLastUpdate(new Timestamp(1429882200));
        expected.setStartDateTime(new SimpleDateFormat("yyyy-MM-dd").parse("2015-10-11"));
        expected.setEndDateTime(new SimpleDateFormat("yyyy-MM-dd").parse("2015-10-11"));
        assertThat(result, equalTo(expected));
    }
}