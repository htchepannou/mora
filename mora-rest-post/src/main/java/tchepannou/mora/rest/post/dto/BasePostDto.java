package tchepannou.mora.rest.post.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import tchepannou.mora.rest.core.dto.ModelDto;
import tchepannou.mora.rest.core.dto.SpaceSummaryDto;
import tchepannou.mora.rest.core.dto.UserSummaryDto;
import tchepannou.mora.rest.core.json.JsonDateSerializer;

import java.util.Date;

public class BasePostDto extends ModelDto{
    //-- Attributes
    protected long id;
    protected String title;
    protected String summary;
    protected Date lastUpdate;
    protected SpaceSummaryDto space;
    protected UserSummaryDto user;


    //-- Getter
    public UserSummaryDto getUser() {
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

    public SpaceSummaryDto getSpace() {
        return space;
    }
}
