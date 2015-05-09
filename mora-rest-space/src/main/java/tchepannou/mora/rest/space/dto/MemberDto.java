package tchepannou.mora.rest.space.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Preconditions;
import tchepannou.mora.core.domain.Member;
import tchepannou.mora.rest.core.dto.ModelDto;
import tchepannou.mora.rest.core.json.JsonDateSerializer;

import java.util.Date;

public class MemberDto extends ModelDto{
    //-- Attributes
    private final long id;
    private final long spaceId;
    private final long userId;
    private final long roleId;
    private final Date creationDate;

    //-- Attribute
    private MemberDto(Builder builder){
        Member member = builder.member;
        id = member.getId();
        spaceId = member.getSpaceId();
        userId = member.getUserId();
        roleId = member.getRoleId();
        creationDate = member.getCreationDate();
    }

    //-- Builder
    public static class Builder {
        private Member member;

        public MemberDto build(){
            Preconditions.checkState(member != null, "member != null");

            return new MemberDto(this);
        }

        public Builder withMember (Member member){
            this.member = member;
            return this;
        }
    }

    //-- Getter
    @JsonSerialize (using=JsonDateSerializer.class)
    public Date getCreationDate() {
        return creationDate;
    }

    public long getId() {
        return id;
    }

    public long getRoleId() {
        return roleId;
    }

    public long getSpaceId() {
        return spaceId;
    }

    public long getUserId() {
        return userId;
    }
}
