package tchepannou.mora.insidesoccer.domain;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.User;

import java.util.Date;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class PartyTest {

    @Test
    public void testToUser() throws Exception {
        // Given
        Date now = new Date();
        Date yesterday = DateUtils.addDays(now, -1);

        Party party = new Party(1);
        party.setDeleted(true);
        party.setTypeId(2);
        party.setOwnerId(3);
        party.setStatus(4);
        party.setCreationDate(yesterday);
        party.setDate(now);

        User user = new User();

        // When
        party.toUser(user);

        // Then
        User expected = new User();
        expected.setId(1);
        expected.setDeleted(true);
        expected.setCreationDate(yesterday);
        expected.setLastUpdate(now);
        assertThat(user, equalTo(expected));
    }

    @Test
    public void testToSpace() throws Exception {
        // Given
        Date now = new Date();
        Date yesterday = DateUtils.addDays(now, -1);

        Party party = new Party(1);
        party.setDeleted(true);
        party.setTypeId(2);
        party.setOwnerId(3);
        party.setStatus(4);
        party.setCreationDate(yesterday);
        party.setDate(now);

        Space space = new Space();

        // When
        party.toSpace(space);

        // Then
        Space expected = new Space();
        expected.setId(1);
        expected.setTypeId(2);
        expected.setUserId(3);
        expected.setDeleted(true);
        expected.setCreationDate(yesterday);
        expected.setLastUpdate(now);
        assertThat(space, equalTo(expected));
    }
}