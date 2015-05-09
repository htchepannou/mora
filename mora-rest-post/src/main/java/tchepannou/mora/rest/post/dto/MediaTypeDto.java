package tchepannou.mora.rest.post.dto;

import com.google.common.base.Preconditions;
import tchepannou.mora.core.domain.MediaType;
import tchepannou.mora.rest.core.dto.ModelDto;

public class MediaTypeDto extends ModelDto{
    private final long id;
    private final String name;

    //-- Constructor
    private MediaTypeDto(Builder builder){
        this.id = builder.type.getId();
        this.name = builder.type.getName();
    }

    //-- Getter
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    //-- Builder
    public static class Builder{
        MediaType type;

        public MediaTypeDto build (){
            Preconditions.checkState(type != null, "type == null");

            return new MediaTypeDto(this);
        }

        public Builder withMediaType(MediaType type){
            this.type = type;
            return this;
        }
    }
}
