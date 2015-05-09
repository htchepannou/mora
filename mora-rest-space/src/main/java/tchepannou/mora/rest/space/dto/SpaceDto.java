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
    private long id;
    private EnumDto type;
    private String name;
    private String abbreviation;
    private String description;
    private String logoUrl;
    private String websiteUrl;
    private String email;
    private String address;
    private Date creationDate;
    private Date lastUpdate;

    //-- Public
    private SpaceDto (){

    }

    //-- Builder
    public static class Builder {
        private Space space;
        private SpaceType spaceType;

        public SpaceDto build (){
            Preconditions.checkState(space != null, "space != null");
            Preconditions.checkState(spaceType != null, "spaceType != null");
            Preconditions.checkState(spaceType.getId() == space.getTypeId(), "spaceType.id != space.typeId");

            SpaceDto result = new SpaceDto();
            result.id = space.getId();
            result.name = space.getName();
            result.abbreviation = space.getAbbreviation();
            result.description = space.getDescription();
            result.logoUrl = space.getLogoUrl();
            result.websiteUrl = space.getWebsiteUrl();
            result.email = space.getEmail();
            result.address = space.getAddress();
            result.creationDate = space.getCreationDate();
            result.lastUpdate = space.getLastUpdate();
            result.type = new EnumDto.Builder().withEnum(this.spaceType).build();

            return result;
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
