package tchepannou.mora.rest.space.dto;

import tchepannou.mora.core.domain.Member;
import tchepannou.mora.rest.core.dto.ModelDto;

public class SaveMemberDto extends ModelDto{
    public long roleId;

    //-- Public
    public void toMember (Member member){
        member.setRoleId(roleId);
    }

    //-- Getter/Setter
    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
}
