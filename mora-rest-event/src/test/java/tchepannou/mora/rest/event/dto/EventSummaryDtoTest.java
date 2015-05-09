package tchepannou.mora.rest.event.dto;

import org.junit.Test;
import tchepannou.mora.core.domain.Event;
import tchepannou.mora.core.domain.EventType;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.SpaceType;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.rest.core.dto.EnumDto;
import tchepannou.mora.rest.core.dto.SpaceSummaryDto;
import tchepannou.mora.rest.core.dto.UserSummaryDto;

import java.time.ZoneId;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class EventSummaryDtoTest {
    @Test
    public void testBuilder (){
        // Given
        User user = new User(1);
        user.setFirstName("Ray");
        user.setLastName("Sponsible");

        Space space = new Space(1, new SpaceType(1, "club"), user);
        space.setName("Test");
        space.setLogoUrl("http://www.google.com/logo.png");

        EventType type = new EventType(EventType.GAME, "game");

        Event event = new Event (1, type, space, user);
        event.setLocation("foo");
        event.setUrl("http://www.google.ca");
        event.setTimezone(ZoneId.of("America/Montreal"));
        event.setRequiresRSVP(true);
        event.setNotes("this is note");
        event.setTitle("title");
        event.setAddress("This is the address");

        // When
        EventSummaryDto result = (EventSummaryDto)new EventSummaryDto.Builder()
                .withUser(user)
                .withSpace(space)
                .withEvent(event)
                .withType(type)
                .build();

        // Then
        UserSummaryDto expectedUser = new UserSummaryDto.Builder().withUser(user).build();
        SpaceSummaryDto expectedSpace = new SpaceSummaryDto.Builder().withSpace(space).build();
        EnumDto expectedType = new EnumDto.Builder().withEnum(type).build();

        assertThat(result.getId(), equalTo(event.getId()));
        assertThat(result.getTitle(), equalTo(event.getTitle()));
        assertThat(result.getStartDateTime(), equalTo(event.getStartDateTime()));
        assertThat(result.getEndDateTime(), equalTo(event.getEndDateTime()));
        assertThat(result.getTimezone(), equalTo("America/Montreal"));
        assertThat(result.getLocation(), equalTo(event.getLocation()));
        assertThat(result.getType(), equalTo(expectedType));
        assertThat(result.getUser(), equalTo(expectedUser));
        assertThat(result.getSpace(), equalTo(expectedSpace));

    }
}