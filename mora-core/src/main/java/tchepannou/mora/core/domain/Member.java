package tchepannou.mora.core.domain;

import com.google.common.base.Preconditions;

import java.sql.Date;

public class Member extends Model{
    //-- Attribute
    private Space space;
    private User user;
    private Role role;
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
        
        this.space = space;
        this.user = user;
        this.role = role;
    }
    
    //-- Getter/Setter
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Space getSpace() {
        return space;
    }

    public void setSpace(Space space) {
        this.space = space;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
