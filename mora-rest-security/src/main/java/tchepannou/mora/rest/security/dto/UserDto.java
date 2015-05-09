package tchepannou.mora.rest.security.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.rest.core.dto.ModelDto;
import tchepannou.mora.rest.core.json.JsonDateSerializer;

import java.util.Date;

public class UserDto extends ModelDto {
    //-- Attributes
    private final long id;
    private final String username;
    private final String firstName;
    private final String lastName;
    private final String name;
    private final String email;
    private final Date creationDate;
    private final Date lastUpdate;

    //-- Constructor
    private UserDto(Builder builder){
        User user = builder.user;
        id = user.getId();
        username = user.getUsername();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        creationDate = user.getCreationDate();
        lastUpdate = user.getLastUpdate();
        name = Joiner.on(' ').join(user.getFirstName(), user.getLastName());
    }

    //-- Builder
    public static class Builder{
        private User user;

        public UserDto build (){
            Preconditions.checkState(this.user != null, "this.user==null");

            return new UserDto(this);
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
