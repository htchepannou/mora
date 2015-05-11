package tchepannou.mora.rest.event.dto;

import org.junit.Before;
import org.junit.Test;
import tchepannou.mora.core.domain.Event;
import tchepannou.mora.core.domain.EventType;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.SpaceType;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.insidesoccer.domain.IsEventType;
import tchepannou.mora.rest.core.dto.EnumDto;
import tchepannou.mora.rest.core.dto.SpaceSummaryDto;
import tchepannou.mora.rest.core.dto.UserSummaryDto;

import java.time.ZoneId;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class EventDtoTest {
    private User user;
    private Space space;
    private EventType type;
    private Event event;

    @Before
    public void setUp (){
        user = new User(1);
        user.setFirstName("Ray");
        user.setLastName("Sponsible");

        space = new Space(1, new SpaceType(1, "club"), user);
        space.setName("Test");
        space.setLogoUrl("http://www.google.com/logo.png");


        type = new EventType(IsEventType.MATCH, "match");

        event = new Event (1, type, space, user);
        event.setLocation("foo");
        event.setUrl("http://www.google.ca");
        event.setTimezone(ZoneId.of("America/Montreal"));
        event.setRequiresRSVP(true);
        event.setNotes("this is note");
        event.setTitle("title");
        event.setAddress("This is the address");
    }
    @Test
    public void testBuilder (){
        EventDto result = (EventDto)new EventDto.Builder()
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
        assertThat(result.getAddress(), equalTo(event.getAddress()));
        assertThat(result.getTitle(), equalTo(event.getTitle()));
        assertThat(result.getStartDateTime(), equalTo(event.getStartDateTime()));
        assertThat(result.getEndDateTime(), equalTo(event.getEndDateTime()));
        assertThat(result.getTimezone(), equalTo("America/Montreal"));
        assertThat(result.getLocation(), equalTo(event.getLocation()));
        assertThat(result.isRequiresRSVP(), equalTo(event.isRequiresRSVP()));
        assertThat(result.getUrl(), equalTo(event.getUrl()));
        assertThat(result.getNotes(), equalTo(event.getNotes()));
        assertThat(result.getType(), equalTo(expectedType));
        assertThat(result.getUser(), equalTo(expectedUser));
        assertThat(result.getSpace(), equalTo(expectedSpace));
    }

    @Test(expected = IllegalStateException.class)
    public void testBuilder_noEvent_throwsIllegalStateException (){
        new EventDto.Builder()
                .withUser(user)
                .withSpace(space)
                .withEvent(null)
                .withType(type)
                .build();
    }


    @Test(expected = IllegalStateException.class)
    public void testBuilder_noUser_throwsIllegalStateException (){
        new EventDto.Builder()
                .withUser(null)
                .withSpace(space)
                .withEvent(event)
                .withType(type)
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuilder_badUser_throwsIllegalStateException (){
        new EventDto.Builder()
                .withUser(new User(9999))
                .withSpace(space)
                .withEvent(event)
                .withType(type)
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuilder_noSpace_throwsIllegalStateException (){
        new EventDto.Builder()
                .withUser(user)
                .withSpace(null)
                .withEvent(event)
                .withType(type)
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuilder_badSpace_throwsIllegalStateException (){
        new EventDto.Builder()
                .withUser(user)
                .withSpace(new Space(999))
                .withEvent(event)
                .withType(type)
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuilder_noEventType_throwsIllegalStateException (){
        new EventDto.Builder()
                .withUser(user)
                .withSpace(space)
                .withEvent(event)
                .withType(null)
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuilder_badEventType_throwsIllegalStateException (){
        new EventDto.Builder()
                .withUser(user)
                .withSpace(space)
                .withEvent(event)
                .withType(new EventType(999))
                .build();
    }
}