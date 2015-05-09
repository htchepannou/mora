package tchepannou.mora.rest.event.dto;

public class EventSummaryDto extends BaseEventDto{
    //-- Inner classes
    public static class Builder extends BaseEventDtoBuilder<EventSummaryDto>{
        @Override
        protected EventSummaryDto createDto() {
            return new EventSummaryDto();
        }
    }
}
