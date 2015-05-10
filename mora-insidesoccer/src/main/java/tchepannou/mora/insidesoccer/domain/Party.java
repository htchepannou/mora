package tchepannou.mora.insidesoccer.domain;

import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.User;

import java.util.Date;

public class Party extends AbstractNode{
    //-- Attribute
    public static final long TYPE_USER = 1;
    public static final long TYPE_TEAM = 3;
    public static final long TYPE_CLUB = 4;
    private Date creationDate;

    // Public
    public Party (){

    }
    public Party(long id){
        super(id);
    }
    public Party(long id, long typeId){
        super(id, typeId);
    }

    //-- Public
    public void toUser (User user){
        user.setId(getId());
        user.setDeleted(this.deleted);
        user.setCreationDate(this.creationDate);
        user.setLastUpdate(this.date);
    }
    
    public void toSpace (Space space){
        space.setId(getId());
        space.setUserId(this.ownerId);
        space.setTypeId(this.typeId);
        space.setDeleted(this.deleted);
        space.setCreationDate(this.creationDate);
        space.setLastUpdate(this.date);
    }

    //-- Getter/Setter
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
