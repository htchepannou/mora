package tchepannou.mora.rest.event.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tchepannou.mora.core.domain.AccessToken;
import tchepannou.mora.core.domain.Event;
import tchepannou.mora.core.domain.EventType;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.SpaceType;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.core.service.AccessTokenService;
import tchepannou.mora.core.service.EventService;
import tchepannou.mora.core.service.EventTypeService;
import tchepannou.mora.core.service.SpaceService;
import tchepannou.mora.core.service.UserService;
import tchepannou.mora.rest.core.exception.NotFoundException;
import tchepannou.mora.rest.event.dto.EventDto;
import tchepannou.mora.rest.event.dto.EventSummaryDto;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyCollection;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EventControllerTest {
    @Mock
    private EventService eventService;

    @Mock
    private EventTypeService eventTypeService;
    
    @Mock
    private SpaceService spaceService;
    
    @Mock
    private UserService userService;
    
    @Mock
    private AccessTokenService accessTokenService;

    @Mock
    Principal principal;

    @InjectMocks
    private EventController controller = new EventController();

    private User user1 = new User(1);
    private User user2 = new User(2);

    private SpaceType spaceType = new SpaceType(1, "team");
    private Space space1 = new Space(1, spaceType, user1);
    private Space space2 = new Space(2, spaceType, user1);

    private EventType eventType = new EventType(1, "game");

    private AccessToken token = new AccessToken (439430, user1);

    @Before
    public void setUp (){
        user1.setFirstName("Ray");
        user1.setLastName("Sponsible");

        user2.setFirstName("John");
        user2.setLastName("Smith");

        when(userService.findById(user1.getId())).thenReturn(user1);
        when(userService.findById(user2.getId())).thenReturn(user2);
        when(userService.findByIds(anyCollection())).thenReturn(Arrays.asList(user1, user2));


        space1.setName("NYSC B97");
        space1.setLogoUrl("http://goo.com/logo97.png");

        space2.setName("NYSC B98");
        space2.setLogoUrl("http://goo.com/logo98.png");

        when(spaceService.findById(space1.getId())).thenReturn(space1);
        when(spaceService.findById(space2.getId())).thenReturn(space2);
        when(spaceService.findByIds(anyCollection())).thenReturn(Arrays.asList(space1, space2));

        when(eventTypeService.findById(eventType.getId())).thenReturn(eventType);
        when(eventTypeService.findAll()).thenReturn(Arrays.asList(eventType));

        token.setValue("43eir43049");
        when(accessTokenService.findByValue(token.getValue())).thenReturn(token);
        when(principal.getName()).thenReturn(token.getValue());
    }

    @Test
    public void testGet() throws Exception {
        // Given
        Event event = new Event(1, eventType, space1, user2);
        when(eventService.findById(event.getId())).thenReturn(event);

        // When
        EventDto result = controller.get(event.getId());

        // Then
        EventDto expected = (EventDto)new EventDto.Builder()
                .withEvent(event)
                .withSpace(space1)
                .withType(eventType)
                .withUser(user2)
                .build();

        assertThat(result).isEqualTo(expected);
    }

    @Test(expected = NotFoundException.class)
    public void testGet_notFound_throwsNotFoundException() throws Exception {
        controller.get(9999);
    }

    @Test
    public void testGetUpcomingEvents() throws Exception {
        // Given
        Event event1 = new Event(1, eventType, space1, user1);
        Event event2 = new Event(2, eventType, space2, user1);
        Event event3 = new Event(3, eventType, space1, user1);
        when(eventService.findIdsUpcomingForUser(user1.getId(), 100, 0)).thenReturn(Arrays.asList(1L, 2L, 3L));
        when(eventService.findByIds(Arrays.asList(1L, 2L, 3L))).thenReturn(Arrays.asList(event1, event2, event3));

        // When
        List<EventSummaryDto> result = controller.upcoming(principal, 100, 0);

        // Then
        EventSummaryDto expected1= (EventSummaryDto)new EventSummaryDto.Builder()
                .withEvent(event1)
                .withSpace(space1)
                .withType(eventType)
                .withUser(user1)
                .build();

        EventSummaryDto expected2= (EventSummaryDto)new EventSummaryDto.Builder()
                .withEvent(event2)
                .withSpace(space2)
                .withType(eventType)
                .withUser(user1)
                .build();

        EventSummaryDto expected3= (EventSummaryDto)new EventSummaryDto.Builder()
                .withEvent(event3)
                .withSpace(space1)
                .withType(eventType)
                .withUser(user1)
                .build();

        assertThat(result).hasSize(3);
        assertThat(result).contains(expected1, expected2, expected3);
    }


}