package tchepannou.mora.rest.post.dto;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.rest.core.dto.ModelDto;

public class UserDto extends ModelDto {
    //-- Private
    private long id;
    private String name;
    private String firstName;
    private String lastName;


    //-- Builder
    public static class Builder{
        private User user ;

        public UserDto build (){
            Preconditions.checkState(this.user != null, "this.user==null");

            UserDto dto = new UserDto();
            dto.id = user.getId();
            dto.firstName = user.getFirstName();
            dto.lastName = user.getLastName();
            dto.name = Joiner.on(' ').join(
                    Strings.nullToEmpty(user.getFirstName())
                    , Strings.nullToEmpty(user.getLastName()));

            return dto;
        }

        public Builder withUser (User user){
            this.user = user;
            return this;
        }
    }

    //-- Getter
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
}
