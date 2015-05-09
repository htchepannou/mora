package tchepannou.mora.rest.event.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import tchepannou.mora.core.domain.Event;
import tchepannou.mora.rest.core.dto.EnumDto;
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
    private final EnumDto type;
    private final SpaceSummaryDto space;
    private final UserSummaryDto user;


    //-- Constructor
    protected BaseEventDto(BaseEventDtoBuilder builder) {
        Event evt = builder.event;
        ZoneId zoneId = evt.getTimezone();

        this.id = evt.getId();
        this.title = evt.getTitle();
        this.startDateTime = evt.getStartDateTime();
        this.endDateTime = evt.getEndDateTime();
        this.location = evt.getLocation();
        this.timezone = zoneId != null ? zoneId.getId() : null;
        this.type = new EnumDto.Builder().withEnum(builder.type).build();
        this.space = new SpaceSummaryDto.Builder().withSpace(builder.space).build();
        this.user = new UserSummaryDto.Builder().withUser(builder.user).build();
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

    public EnumDto getType() {
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
