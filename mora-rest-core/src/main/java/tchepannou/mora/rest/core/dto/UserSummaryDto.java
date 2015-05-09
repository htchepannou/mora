package tchepannou.mora.rest.core.dto;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import tchepannou.mora.core.domain.User;

public class UserSummaryDto extends ModelDto {
    //-- Private
    private long id;
    private String name;


    //-- Public
    public UserSummaryDto(){
    }
    public UserSummaryDto (long id, String name){
        this.id = id;
        this.name = name;
    }

    //-- Builder
    public static class Builder{
        private User user ;

        public UserSummaryDto build (){
            Preconditions.checkState(this.user != null, "this.user==null");

            UserSummaryDto dto = new UserSummaryDto();
            dto.id = user.getId();
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
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
