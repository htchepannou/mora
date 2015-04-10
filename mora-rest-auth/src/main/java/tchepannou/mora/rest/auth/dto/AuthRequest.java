package tchepannou.mora.rest.auth.dto;

import tchepannou.mora.rest.core.dto.ModelDto;

import javax.validation.constraints.NotNull;

public class AuthRequest extends ModelDto{
    //-- Attributes
    @NotNull
    private String usernameOrEmail;

    @NotNull
    private String password;

    //-- Getter/Setter
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }
}
