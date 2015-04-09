package tchepannou.mora.rest.user.dto;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.wordnik.swagger.annotations.ApiModel;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.rest.core.dto.ModelDto;

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
    public String getName() {
        return name;
    }

    public Date getCreationDate() {
        return creationDate;
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

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public String getUsername() {
        return username;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
