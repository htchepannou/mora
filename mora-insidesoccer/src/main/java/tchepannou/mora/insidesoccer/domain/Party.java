package tchepannou.mora.insidesoccer.domain;

import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.User;

import java.util.Date;

public class Party extends AbstractNode{
    //-- Attribute
    private Date creationDate;

    // Public
    public Party (){

    }
    public Party(long id){
        super(id);
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
