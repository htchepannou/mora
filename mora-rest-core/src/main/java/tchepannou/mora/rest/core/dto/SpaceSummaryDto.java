package tchepannou.mora.rest.core.dto;

import com.google.common.base.Preconditions;
import tchepannou.mora.core.domain.Space;

public class SpaceSummaryDto extends ModelDto {
    //-- Attributes
    private final long id;
    private final String name;
    private final String logoUrl;

    //-- Constructor
    private SpaceSummaryDto(Builder builder){
        this.id = builder.space.getId();
        this.name = builder.space.getName();
        this.logoUrl = builder.space.getLogoUrl();
    }

    //-- Builder
    public static class Builder {
        private Space space;

        public SpaceSummaryDto build (){
            Preconditions.checkState(space != null, "space != null");

            return new SpaceSummaryDto(this);
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
