package tchepannou.mora.rest.space.dto;

import com.google.common.base.Strings;
import tchepannou.mora.core.domain.Space;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveSpaceDto {
    //-- Attributes
    @NotNull
    @Size (min=1, max=100)
    private String name;

    @Size (max=5)
    private String abbreviation;

    private String description;

    @Size (max=255)
    private String websiteUrl;

    @Size (max=255)
    private String email;

    @Size (max=255)
    private String address;


    //-- Public
    public void toSpace (Space space){
        if (!Strings.isNullOrEmpty(this.name)){
            space.setName(this.name);
        }
        if (!Strings.isNullOrEmpty(this.abbreviation)){
            space.setAbbreviation(this.abbreviation);
        }
        if (!Strings.isNullOrEmpty(this.description)){
            space.setDescription(this.description);
        }
        if (!Strings.isNullOrEmpty(this.websiteUrl)){
            space.setWebsiteUrl(this.websiteUrl);
        }
        if (!Strings.isNullOrEmpty(this.email)){
            space.setEmail(this.email);
        }
        if (!Strings.isNullOrEmpty(this.address)){
            space.setAddress(this.address);
        }
    }

    //-- Getter/Setter
    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }
}
