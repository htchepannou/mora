package tchepannou.mora.insidesoccer.domain;

import tchepannou.mora.core.domain.Model;
import tchepannou.mora.core.domain.SoftDeleteSupport;

import java.util.Date;

public class Party extends Model implements SoftDeleteSupport{
    //-- Attribute
    public long typeId;
    public long ownerId;
    public boolean deleted;
    public int status;
    public Date date;
    public Date creationDate;


    //-- SoftDeleteSupport overrides
    public boolean isDeleted() {
        return deleted;
    }

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
