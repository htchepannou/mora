package tchepannou.mora.rest.auth.dto;

import com.google.common.base.Preconditions;
import tchepannou.mora.core.domain.AccessToken;
import tchepannou.mora.rest.core.dto.ModelDto;

import java.util.Date;

public class AccessTokenDto extends ModelDto{
    //-- Attributes
    private String value;
    private Date creationDate;
    private Date expiryDate;

    //-- Constructor
    private AccessTokenDto(){
    }

    //-- Builder
    public static class Builder{
        private AccessToken token;

        public AccessTokenDto build (){
            Preconditions.checkState(token != null, "token==null");

            AccessTokenDto dto = new AccessTokenDto();
            dto.value = token.getValue();
            dto.creationDate = token.getCreationDate();
            dto.expiryDate = token.getExpiryDate();
            return dto;
        }

        public Builder withAccessToken (AccessToken token){
            this.token = token;
            return this;
        }
    }

    //-- Getter
    public Date getCreationDate() {
        return creationDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public String getValue() {
        return value;
    }
}
