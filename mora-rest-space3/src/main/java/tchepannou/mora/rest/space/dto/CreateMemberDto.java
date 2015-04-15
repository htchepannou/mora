package tchepannou.mora.rest.space.dto;

import tchepannou.mora.core.domain.Member;

public class CreateMemberDto extends SaveMemberDto{
    public long spaceId;
    public long userId;

    //-- Public
    @Override
    public void toMember (Member member){
        super.toMember(member);

        member.setSpaceId(spaceId);
        member.setUserId(userId);
    }

    //-- Getter/Setter

    public long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(long spaceId) {
        this.spaceId = spaceId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
