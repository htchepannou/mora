package tchepannou.mora.rest.event.dto;

import tchepannou.mora.core.domain.Event;

public class EventDto extends BaseEventDto{
    //-- Attributes
    private boolean requiresRSVP;
    private String address;
    private String url;
    private String notes;


    //-- Constructor
    private EventDto(Builder builder) {
        super(builder);

        Event event = builder.event;
        this.address = event.getAddress();
        this.requiresRSVP = event.isRequiresRSVP();
        this.url = event.getUrl();
        this.notes = event.getNotes();
    }

    //-- Getter
    public String getAddress() {
        return address;
    }

    public String getNotes() {
        return notes;
    }

    public boolean isRequiresRSVP() {
        return requiresRSVP;
    }

    public String getUrl() {
        return url;
    }

    //-- Inner
    public static class Builder extends BaseEventDtoBuilder<EventDto>{
        @Override
        protected EventDto createDto() {
            return new EventDto(this);
        }
    }
}
