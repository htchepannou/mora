package tchepannou.mora.rest.security.dto;

import com.wordnik.swagger.annotations.ApiModel;
import org.hibernate.validator.constraints.Email;
import tchepannou.mora.core.domain.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel ()
public class CreateUserDto extends SaveUserDto {
    //-- Attributes
    @NotNull
    @Size (min=6, max=16)
    private String username;

    @NotNull
    @Size (min=6)
    private String password;


    //-- UserSaveRequest overrides
    @Override
    public void toUser(User user) {
        super.toUser(user);

        user.setUsername(username);
    }


    @Override
    @NotNull
    @Email
    public String getEmail() {
        return super.getEmail();
    }

    //-- Getter/Setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
