package tchepannou.mora.rest.post.dto;

import com.google.common.base.Preconditions;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.rest.core.dto.ModelDto;

public class SpaceDto extends ModelDto {
    //-- Attributes
    private long id;
    private String name;
    private String logoUrl;

    //-- Builder
    public static class Builder {
        private Space space;

        public SpaceDto build (){
            Preconditions.checkState(space != null, "space != null");

            SpaceDto result = new SpaceDto();
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
