package tchepannou.mora.rest.space.dto;

import com.google.common.base.Preconditions;
import tchepannou.mora.core.domain.SpaceType;

public class SpaceTypeDto {
    //-- Attributes
    private long id;
    private String name;


    //-- Constructor
    private SpaceTypeDto (){
    }
    public SpaceTypeDto (long id, String name){
        this.id = id;
        this.name = name;
    }

    //-- Getter
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    //-- Builder
    public static class Builder {
        private SpaceType spaceType;

        public SpaceTypeDto build (){
            Preconditions.checkState(spaceType != null, "spaceType == null");

            SpaceTypeDto result = new SpaceTypeDto();
            result.id = spaceType.getId();
            result.name = spaceType.getName();
            return result;
        }

        public Builder withSpaceType (SpaceType spaceType){
            this.spaceType = spaceType;
            return this;
        }
    }
}
