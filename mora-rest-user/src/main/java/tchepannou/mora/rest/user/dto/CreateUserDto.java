package tchepannou.mora.rest.user.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import tchepannou.mora.core.domain.User;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@ApiModel ()
public class CreateUserDto extends SaveUserDto {
    //-- Attributes
    @NotNull
    @Min(6)
    @ApiModelProperty(required = true)
    private String username;

    @NotNull
    @Min(6)
    @ApiModelProperty(required = true)
    private String password;


    //-- UserSaveRequest overrides
    @Override
    public void toUser(User user) {
        super.toUser(user);

        user.setUsername(username);
    }


    @Override
    @NotNull
    @ApiModelProperty(required = true)
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    @NotNull
    @ApiModelProperty(required = true)
    public String getFirstName() {
        return super.getFirstName();
    }

    @Override
    @NotNull
    @ApiModelProperty(required = true)
    public String getLastName() {
        return super.getLastName();
    }

    //-- Getter/Setter
    @ApiModelProperty(required = true)
    public String getUsername() {
        return username;
    }

    @ApiModelProperty(required = true)
    public void setUsername(String username) {
        this.username = username;
    }

    @ApiModelProperty(required = true)
    public String getPassword() {
        return password;
    }

    @ApiModelProperty(required = true)
    public void setPassword(String password) {
        this.password = password;
    }
}
