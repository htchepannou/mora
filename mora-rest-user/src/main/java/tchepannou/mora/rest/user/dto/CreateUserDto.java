package tchepannou.mora.rest.user.dto;

import com.wordnik.swagger.annotations.ApiModel;
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
    @Size (min=6, max=100)
    private String password;


    //-- UserSaveRequest overrides
    @Override
    public void toUser(User user) {
        super.toUser(user);

        user.setUsername(username);
    }


    @Override
    @NotNull
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    @NotNull
    @Size (min=1)
    public String getFirstName() {
        return super.getFirstName();
    }

    @Override
    @NotNull
    @Size (min=1)
    public String getLastName() {
        return super.getLastName();
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
