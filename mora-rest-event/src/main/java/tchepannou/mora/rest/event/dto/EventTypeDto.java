package tchepannou.mora.rest.event.dto;

import tchepannou.mora.rest.core.dto.ModelDto;

public class EventTypeDto extends ModelDto{
    private long id;
    private String name;

    //-- Constructor
    public EventTypeDto(long id, String name){
        this.id = id;
        this.name = name;
    }

    //-- Getter
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
