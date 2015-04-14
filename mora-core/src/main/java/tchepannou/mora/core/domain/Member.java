package tchepannou.mora.core.domain;

import com.google.common.base.Preconditions;

import java.util.Date;


public class Member extends Model{
    //-- Attribute
    private long spaceId;
    private long userId;
    private long roleId;
    private Date creationDate;
    
    //-- Public
    public Member(){
    }
    public Member(Space space, User user, Role role){
        Preconditions.checkArgument(space != null, "space == null");
        Preconditions.checkArgument(space.getId() > 0, "spaceid <= 0");
        
        Preconditions.checkArgument(user != null, "user == null");
        Preconditions.checkArgument(user.getId() > 0, "userid <= 0");
        
        Preconditions.checkArgument(role != null, "role == null");
        Preconditions.checkArgument(role.getId() > 0, "roleid <= 0");
        
        this.spaceId = space.getId();
        this.userId = user.getId();
        this.roleId = role.getId();
    }
    
    //-- Getter/Setter
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

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
