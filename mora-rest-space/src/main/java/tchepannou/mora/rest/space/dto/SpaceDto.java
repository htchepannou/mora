package tchepannou.mora.rest.space.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Preconditions;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.SpaceType;
import tchepannou.mora.rest.core.dto.EnumDto;
import tchepannou.mora.rest.core.dto.ModelDto;
import tchepannou.mora.rest.core.json.JsonDateSerializer;

import java.util.Date;

public class SpaceDto extends ModelDto{
    //-- Attributes
    private final long id;
    private final EnumDto type;
    private final String name;
    private final String abbreviation;
    private final String description;
    private final String logoUrl;
    private final String websiteUrl;
    private final String email;
    private final String address;
    private final Date creationDate;
    private final Date lastUpdate;

    //-- Public
    private SpaceDto (Builder builder){
        Space space = builder.space;
        
        id = space.getId();
        name = space.getName();
        abbreviation = space.getAbbreviation();
        description = space.getDescription();
        logoUrl = space.getLogoUrl();
        websiteUrl = space.getWebsiteUrl();
        email = space.getEmail();
        address = space.getAddress();
        creationDate = space.getCreationDate();
        lastUpdate = space.getLastUpdate();
        type = new EnumDto.Builder().withEnum(builder.spaceType).build();
    }

    //-- Builder
    public static class Builder {
        private Space space;
        private SpaceType spaceType;

        public SpaceDto build (){
            Preconditions.checkState(space != null, "space != null");
            Preconditions.checkState(spaceType != null, "spaceType != null");
            Preconditions.checkState(spaceType.getId() == space.getTypeId(), "spaceType.id != space.typeId");

            return new SpaceDto(this);
        }

        public Builder withSpace (Space space){
            this.space = space;
            return this;
        }
        public Builder withSpaceType (SpaceType type){
            this.spaceType = type;
            return this;
        }
    }

    //-- Getter


    public String getAbbreviation() {
        return abbreviation;
    }

    public String getAddress() {
        return address;
    }

    @JsonSerialize (using=JsonDateSerializer.class)
    public Date getCreationDate() {
        return creationDate;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public long getId() {
        return id;
    }

    @JsonSerialize (using=JsonDateSerializer.class)
    public Date getLastUpdate() {
        return lastUpdate;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public String getName() {
        return name;
    }

    public EnumDto getType() {
        return type;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }
}
