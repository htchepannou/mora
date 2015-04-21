package tchepannou.mora.rest.post.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import tchepannou.mora.rest.core.dto.ModelDto;
import tchepannou.mora.rest.core.json.JsonDateSerializer;

import java.util.Date;

public class BasePostDto extends ModelDto{
    //-- Attributes
    protected long id;
    protected String title;
    protected String summary;
    protected Date lastUpdate;
    protected SpaceDto space;
    protected UserDto user;


    //-- Getter
    public UserDto getUser() {
        return user;
    }

    public long getId() {
        return id;
    }

    @JsonSerialize (using=JsonDateSerializer.class)
    public Date getLastUpdate() {
        return lastUpdate;
    }

    public String getSummary() {
        return summary;
    }

    public String getTitle() {
        return title;
    }

    public SpaceDto getSpace() {
        return space;
    }
}
