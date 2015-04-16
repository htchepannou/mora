package tchepannou.mora.insidesoccer.domain;

import tchepannou.mora.core.domain.Model;
import tchepannou.mora.core.domain.SoftDeleteSupport;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.User;

import java.util.Date;

public class Party extends Model implements SoftDeleteSupport{
    //-- Attribute
    private long typeId;
    private long ownerId;
    private boolean deleted;
    private int status;
    private Date date;
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
        space.setDeleted(this.deleted);
        space.setCreationDate(this.creationDate);
        space.setLastUpdate(this.date);
    }

    //-- SoftDeleteSupport overrides
    @Override
    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }


    //-- Getter/Setter
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }
}
