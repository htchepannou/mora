package tchepannou.mora.rest.core.dto;

import com.google.common.base.Preconditions;
import tchepannou.mora.core.domain.Space;

public class SpaceSummaryDto extends ModelDto {
    //-- Attributes
    private long id;
    private String name;
    private String logoUrl;

    //-- Constructor
    public SpaceSummaryDto(){

    }
    public SpaceSummaryDto(long id, String name, String logoUrl){
        this.id = id;
        this.name = name;
        this.logoUrl = logoUrl;
    }

    //-- Builder
    public static class Builder {
        private Space space;

        public SpaceSummaryDto build (){
            Preconditions.checkState(space != null, "space != null");

            SpaceSummaryDto result = new SpaceSummaryDto();
            result.id = space.getId();
            result.name = space.getName();
            result.logoUrl = space.getLogoUrl();

            return result;
        }

        public Builder withSpace (Space space){
            this.space = space;
            return this;
        }
    }

    //-- Getter
    public long getId() {
        return id;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public String getName() {
        return name;
    }
}
