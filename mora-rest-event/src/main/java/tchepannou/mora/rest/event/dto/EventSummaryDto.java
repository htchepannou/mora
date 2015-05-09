package tchepannou.mora.rest.event.dto;

public class EventSummaryDto extends BaseEventDto{
    private EventSummaryDto(Builder builder) {
        super(builder);
    }

    //-- Inner classes
    public static class Builder extends BaseEventDtoBuilder<EventSummaryDto>{
        @Override
        protected EventSummaryDto createDto() {
            return new EventSummaryDto(this);
        }
    }
}
