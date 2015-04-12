package tchepannou.mora.rest.user.dto;

import com.google.common.base.Strings;
import com.wordnik.swagger.annotations.ApiModel;
import org.hibernate.validator.constraints.Email;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.rest.core.dto.ModelDto;

@ApiModel ()
public class SaveUserDto extends ModelDto {
    //-- Attributes
    private String firstName;
    private String lastName;

    @Email
    private String email;

    //-- Public
    public void toUser (User user){
        if (!Strings.isNullOrEmpty(firstName)) {
            user.setFirstName(firstName);
        }

        if (!Strings.isNullOrEmpty(lastName)) {
            user.setLastName(lastName);
        }

        if (!Strings.isNullOrEmpty(email)) {
            user.setEmail(email);
        }
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
