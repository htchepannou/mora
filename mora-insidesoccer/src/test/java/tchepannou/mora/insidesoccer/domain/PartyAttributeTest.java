package tchepannou.mora.insidesoccer.domain;

import org.junit.Test;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.SpaceType;
import tchepannou.mora.core.domain.User;

import java.util.Arrays;
import java.util.Set;

import static org.hamcrest.Matchers.*;
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
    public void testToSpace() throws Exception {
        // Given
        Party party1 = new Party(1);
        Party party2 = new Party(2);

        PartyAttribute attr11 = new PartyAttribute(1, party1, PartyAttribute.EMAIL,  "info@newyorksoccerclub.org");
        PartyAttribute attr12 = new PartyAttribute(2, party1, PartyAttribute.NAME, "New York Soccer Club");
        PartyAttribute attr13 = new PartyAttribute(3, party1, PartyAttribute.CITY, "Chappaqua");
        PartyAttribute attr14 = new PartyAttribute(4, party1, PartyAttribute.COUNTRY, "US");
        PartyAttribute attr15 = new PartyAttribute(5, party1, PartyAttribute.STATE, "NY");
        PartyAttribute attr16 = new PartyAttribute(6, party1, PartyAttribute.WEBSITE_URL, "http://newyorksoccerclub.org");
        PartyAttribute attr17 = new PartyAttribute(7, party1, PartyAttribute.LOGO_URL, "http://newyorksoccerclub.org/logo.png");
        PartyAttribute attr18 = new PartyAttribute(7, party1, PartyAttribute.DESCRIPTION, "description1");

        PartyAttribute attr21 = new PartyAttribute(1, party2, PartyAttribute.EMAIL,  "user1@gmail.com");
        PartyAttribute attr24 = new PartyAttribute(3, party2, PartyAttribute.NAME, "Test");

        Space space = new Space (1, new SpaceType(1), new User(1));

        // When
        PartyAttribute.toSpace(Arrays.asList(attr21, attr24, attr11, attr12, attr13, attr14, attr15, attr16, attr17, attr18), space);

        // Then
        Space expected = new Space (1, new SpaceType(1), new User(1));
        expected.setEmail("info@newyorksoccerclub.org");
        expected.setName("New York Soccer Club");
        expected.setAddress("Chappaqua, US");
        expected.setWebsiteUrl("http://newyorksoccerclub.org");
        expected.setDescription("description1");
        expected.setLogoUrl("http://newyorksoccerclub.org/logo.png");
        assertThat(space, equalTo(expected));
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