package tchepannou.mora.rest.event.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import tchepannou.mora.rest.core.dto.ModelDto;
import tchepannou.mora.rest.core.dto.SpaceSummaryDto;
import tchepannou.mora.rest.core.dto.UserSummaryDto;
import tchepannou.mora.rest.core.json.JsonDateSerializer;

import java.util.Date;

public class BaseEventDto extends ModelDto{
    //-- Attributes
    protected long id;
    protected String title;
    protected Date startDateTime;
    protected Date endDateTime;
    protected String location;
    protected EventTypeDto type;
    protected SpaceSummaryDto space;
    protected UserSummaryDto user;


    //-- Getter
    @JsonSerialize (using=JsonDateSerializer.class)
    public Date getEndDateTime() {
        return endDateTime;
    }

    @JsonSerialize (using=JsonDateSerializer.class)
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
}
