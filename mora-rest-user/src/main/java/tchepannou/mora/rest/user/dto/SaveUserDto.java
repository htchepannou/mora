package tchepannou.mora.rest.user.dto;

import com.wordnik.swagger.annotations.ApiModel;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.rest.core.dto.ModelDto;

@ApiModel ()
public class SaveUserDto extends ModelDto {
    //-- Attributes
    private String firstName;
    private String lastName;
    private String email;

    //-- Public
    public void toUser (User user){
        user.setFirstName(getFirstName());
        user.setLastName(getLastName());
        user.setEmail(getEmail());
    }


    //-- Getter/Setter
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
