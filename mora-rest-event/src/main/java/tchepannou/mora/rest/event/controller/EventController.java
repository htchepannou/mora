package tchepannou.mora.rest.event.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tchepannou.mora.core.domain.AccessToken;
import tchepannou.mora.core.domain.Event;
import tchepannou.mora.core.domain.EventType;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.core.service.AccessTokenService;
import tchepannou.mora.core.service.EventService;
import tchepannou.mora.core.service.EventTypeService;
import tchepannou.mora.core.service.SpaceService;
import tchepannou.mora.core.service.UserService;
import tchepannou.mora.rest.core.controller.BaseRestController;
import tchepannou.mora.rest.core.exception.NotFoundException;
import tchepannou.mora.rest.event.dto.EventDto;
import tchepannou.mora.rest.event.dto.EventSummaryDto;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class EventController extends BaseRestController {
    //-- Attributes
    @Autowired
    private EventService eventService;

    @Autowired
    private EventTypeService eventTypeService;
    
    @Autowired
    private SpaceService spaceService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private AccessTokenService accessTokenService;
    
    //-- Public
    @RequestMapping ("/events/{eventId}")
    public EventDto get (@PathVariable
                             long eventId){
        Event event = eventService.findById(eventId);
        if (event == null){
            throw new NotFoundException(eventId);
        }

        Space space = spaceService.findById(event.getSpaceId());
        User user = userService.findById(event.getUserId());
        EventType type = eventTypeService.findById(event.getTypeId());
        return (EventDto)new EventDto.Builder()
                .withType(type)
                .withEvent(event)
                .withSpace(space)
                .withUser(user)
                .build();
    }

    @RequestMapping ("/events/upcoming")
    public List<EventSummaryDto> upcoming (@AuthenticationPrincipal Principal currentToken, @RequestParam int limit, @RequestParam int offset) {
        /* get the events */
        long userId = getCurrentUserId(currentToken);

        List<Long> ids = eventService.findIdsUpcomingForUser(userId, limit, offset);
        List<Event> events = eventService.findByIds(ids);

        Map<Long, User> users = toUserMap(events);
        Map<Long, Space> spaces = toSpaceMap(events);
        Map<Long, EventType> types = toEventTypeMap();

        return events.stream()
                .map(event ->
                        // @formatter:off
                        (EventSummaryDto) new EventSummaryDto.Builder()
                                .withEvent(event)
                                .withSpace(spaces.get(event.getSpaceId()))
                                .withUser(users.get(event.getUserId()))
                                .withType(types.get(event.getTypeId()))
                                .build())
                        // @formatter:on
                .collect(Collectors.toList());

    }
    

    //-- Private
    private long getCurrentUserId (Principal currentToken) {
        String token = currentToken.getName();
        AccessToken accessToken = accessTokenService.findByValue(token);
        return accessToken.getUserId();
    }

    private Map<Long, EventType> toEventTypeMap (){
        return EventType.map(eventTypeService.findAll());
    }

    private Map<Long, User> toUserMap(List<Event> events){
        Set<Long> ids = events.stream()
                .map(event -> event.getUserId())
                .collect(Collectors.toSet());

        List<User> users = userService.findByIds(ids);
        return User.map(users);
    }

    private Map<Long, Space> toSpaceMap(List<Event> events){
        Set<Long> ids = events.stream()
                .map(event -> event.getSpaceId())
                .collect(Collectors.toSet());
        
        List<Space> spaces = spaceService.findByIds(ids);
        return Space.map(spaces);
    }

}
