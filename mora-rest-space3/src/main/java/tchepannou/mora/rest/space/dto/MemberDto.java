package tchepannou.mora.rest.space.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Preconditions;
import tchepannou.mora.core.domain.Member;
import tchepannou.mora.rest.core.dto.ModelDto;
import tchepannou.mora.rest.core.json.JsonDateSerializer;

import java.util.Date;

public class MemberDto extends ModelDto{
    //-- Attributes
    private long id;
    private long spaceId;
    private long userId;
    private long roleId;
    private Date creationDate;

    //-- Attribute
    private MemberDto(){
    }

    //-- Builder
    public static class Builder {
        private Member member;

        public MemberDto build(){
            Preconditions.checkState(member != null, "member != null");

            MemberDto result = new MemberDto();
            result.id = member.getId();
            result.spaceId = member.getSpaceId();
            result.userId = member.getUserId();
            result.roleId = member.getRoleId();
            result. creationDate = member.getCreationDate();
            return result;
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