package tchepannou.mora.insidesoccer.domain;

import org.junit.Test;
import tchepannou.mora.core.domain.User;

import java.util.Arrays;
import java.util.Set;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class PartyAttributeTest {

    @Test
    public void testToUser() throws Exception {
        // Given
        Party party1 = new Party(1);
        Party party2 = new Party(2);

        PartyAttribute attr11 = new PartyAttribute(1, party1, PartyAttribute.EMAIL,  "user@gmail.com");
        PartyAttribute attr12 = new PartyAttribute(2, party1, PartyAttribute.FIRST_NAME, "Ray");
        PartyAttribute attr13 = new PartyAttribute(3, party1, PartyAttribute.LAST_NAME, "Sponsible");
        PartyAttribute attr14 = new PartyAttribute(3, party1, PartyAttribute.USERNAME, "ray.sponsible");

        PartyAttribute attr21 = new PartyAttribute(1, party2, PartyAttribute.EMAIL,  "user1@gmail.com");
        PartyAttribute attr24 = new PartyAttribute(3, party2, PartyAttribute.USERNAME, "ray.sponbile1");

        User user = new User (1);

        // When
        PartyAttribute.toUser(Arrays.asList(attr21, attr24, attr11, attr12, attr13, attr14), user);

        // Then
        User expected = new User(1);
        expected.setEmail("user@gmail.com");
        expected.setFirstName("Ray");
        expected.setLastName("Sponsible");
        expected.setUsername("ray.sponsible");
        assertThat(user, equalTo(expected));
    }

    @Test
    public void testToPartyIdSet() throws Exception {
        // Given
        Party party1 = new Party(1);
        Party party2 = new Party(2);

        PartyAttribute attr11 = new PartyAttribute(1, party1, PartyAttribute.EMAIL,  "user@gmail.com");
        PartyAttribute attr12 = new PartyAttribute(2, party1, PartyAttribute.FIRST_NAME, "Ray");
        PartyAttribute attr13 = new PartyAttribute(3, party1, PartyAttribute.LAST_NAME, "Sponsible");
        PartyAttribute attr14 = new PartyAttribute(3, party1, PartyAttribute.USERNAME, "ray.sponsible");

        PartyAttribute attr21 = new PartyAttribute(1, party2, PartyAttribute.EMAIL,  "user1@gmail.com");
        PartyAttribute attr24 = new PartyAttribute(3, party2, PartyAttribute.USERNAME, "ray.sponbile1");

        User user = new User (1);

        // When
        Set<Long> result = PartyAttribute.toPartyIdSet(Arrays.asList(attr21, attr24, attr11, attr12, attr13, attr14));

        // Then
        assertThat(result, hasSize(2));
        assertThat(result, hasItems(1L, 2L));
    }
}