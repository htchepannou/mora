package tchepannou.mora.rest.event.dto;

import com.google.common.base.Preconditions;
import tchepannou.mora.core.domain.Event;
import tchepannou.mora.core.domain.EventType;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.rest.core.dto.SpaceSummaryDto;
import tchepannou.mora.rest.core.dto.UserSummaryDto;

//-- Builder
public abstract class BaseEventDtoBuilder<T extends BaseEventDto> {
    protected Event event;
    protected User user;
    protected Space space;
    protected EventType type;

    protected abstract T createDto();

    public T build (){
        Preconditions.checkState(event != null, "event == null");

        Preconditions.checkState(type != null, "type == null");
        Preconditions.checkState(type.getId() == event.getTypeId(), "event.typeId != type.id");

        Preconditions.checkState(space != null, "space == null");
        Preconditions.checkState(space.getId() == event.getSpaceId(), "event.spaceId != space.id");

        Preconditions.checkState(user != null, "user == null");
        Preconditions.checkState(user.getId() == event.getUserId(), "event.userId != user.id");

        T result = createDto();

        result.id = event.getId();
        result.title = event.getTitle();
        result.startDateTime = event.getStartDateTime();
        result.endDateTime = event.getEndDateTime();
        result.location = event.getLocation();
        result.space = new SpaceSummaryDto.Builder().withSpace(space).build();
        result.user = new UserSummaryDto.Builder().withUser(user).build();
        result.type = new EventTypeDto(type.getId(), type.getName());
        return result;
    }


    public BaseEventDtoBuilder withEvent(Event event){
        this.event = event;
        return this;
    }
    public BaseEventDtoBuilder withSpace(Space space){
        this.space = space;
        return this;
    }
    public BaseEventDtoBuilder withUser (User author){
        this.user = author;
        return this;
    }

    public BaseEventDtoBuilder withType (EventType type){
        this.type = type;
        return this;
    }
}
