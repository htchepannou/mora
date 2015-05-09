package tchepannou.mora.rest.event.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import tchepannou.mora.core.domain.Event;
import tchepannou.mora.core.domain.EventType;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.rest.core.dto.ModelDto;
import tchepannou.mora.rest.core.dto.SpaceSummaryDto;
import tchepannou.mora.rest.core.dto.UserSummaryDto;
import tchepannou.mora.rest.core.json.JsonDateSerializer;

import java.time.ZoneId;
import java.util.Date;

public class BaseEventDto extends ModelDto {
    //-- Attributes
    private final long id;
    private final String title;
    private final Date startDateTime;
    private final Date endDateTime;
    private final String timezone;
    private final String location;
    private final EventTypeDto type;
    private final SpaceSummaryDto space;
    private final UserSummaryDto user;


    //-- Constructor
    protected BaseEventDto(BaseEventDtoBuilder builder) {
        Event event = builder.event;
        EventType type = builder.type;
        Space space = builder.space;
        User user = builder.user;
        ZoneId timezone = event.getTimezone();

        this.id = event.getId();
        this.title = event.getTitle();
        this.startDateTime = event.getStartDateTime();
        this.endDateTime = event.getEndDateTime();
        this.location = event.getLocation();
        this.timezone = timezone != null ? timezone.getId() : null;
        this.type = new EventTypeDto(type.getId(), type.getName());
        this.space = new SpaceSummaryDto.Builder().withSpace(space).build();
        this.user = new UserSummaryDto.Builder().withUser(user).build();
    }

    //-- Getter
    @JsonSerialize (using = JsonDateSerializer.class)
    public Date getEndDateTime() {
        return endDateTime;
    }

    @JsonSerialize (using = JsonDateSerializer.class)
    public Date getStartDateTime() {
        return startDateTime;
    }

    public UserSummaryDto getUser() {
        return user;
    }

    public EventTypeDto getType() {
        return type;
    }

    public long getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public String getTitle() {
        return title;
    }

    public SpaceSummaryDto getSpace() {
        return space;
    }

    public String getTimezone() {
        return timezone;
    }
}
