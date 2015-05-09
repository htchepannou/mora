package tchepannou.mora.rest.core.dto;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import tchepannou.mora.core.domain.User;

public class UserSummaryDto extends ModelDto {
    //-- Private
    private final long id;
    private final String name;


    //-- Public
    private UserSummaryDto (Builder builder){
        User user = builder.user;

        this.id = user.getId();
        this.name = Joiner.on(' ').join(Strings.nullToEmpty(user.getFirstName()), Strings.nullToEmpty(user.getLastName()));
    }

    //-- Builder
    public static class Builder{
        private User user ;

        public UserSummaryDto build (){
            Preconditions.checkState(this.user != null, "this.user==null");

            return new UserSummaryDto(this);
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
