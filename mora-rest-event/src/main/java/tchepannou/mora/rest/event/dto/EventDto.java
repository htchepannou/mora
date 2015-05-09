package tchepannou.mora.rest.event.dto;

import java.time.ZoneId;

public class EventDto extends BaseEventDto{
    //-- Attributes
    private boolean requiresRSVP;
    private String address;
    private String url;
    private String notes;
    private ZoneId timezone;

    //-- Getter
    public String getAddress() {
        return address;
    }

    public String getLocation() {
        return location;
    }

    public String getNotes() {
        return notes;
    }

    public boolean isRequiresRSVP() {
        return requiresRSVP;
    }

    public ZoneId getTimezone() {
        return timezone;
    }

    public String getUrl() {
        return url;
    }
}
