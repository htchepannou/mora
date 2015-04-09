package tchepannou.mora.rest.user.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.wordnik.swagger.annotations.ApiModel;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.rest.core.dto.ModelDto;
import tchepannou.mora.rest.core.json.JsonDateSerializer;

import java.util.Date;

@ApiModel ("User")
public class UserDto extends ModelDto {
    //-- Attributes
    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private String name;
    private String email;
    private Date creationDate;
    private Date lastUpdate;

    //-- Constructor
    private UserDto(){
    }

    //-- Builder
    public static class Builder{
        private User user ;

        public UserDto build (){
            Preconditions.checkState(this.user != null, "this.user==null");

            UserDto dto = new UserDto();
            dto.id = user.getId();
            dto.username = user.getUsername();
            dto.firstName = user.getFirstName();
            dto.lastName = user.getLastName();
            dto.email = user.getEmail();
            dto.creationDate = user.getCreationDate();
            dto.lastUpdate = user.getLastUpdate();
            dto.name = Joiner.on(' ').join(user.getFirstName(), user.getLastName());

            return dto;
        }

        public Builder withUser (User user){
            this.user = user;
            return this;
        }
    }

    //-- Getter

    @JsonSerialize (using=JsonDateSerializer.class)
    public Date getCreationDate() {
        return creationDate;
    }

    @JsonSerialize (using=JsonDateSerializer.class)
    public Date getLastUpdate() {
        return lastUpdate;
    }


    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }
}
