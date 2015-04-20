package tchepannou.mora.insidesoccer.domain;

import tchepannou.mora.core.domain.Model;
import tchepannou.mora.core.domain.SoftDeleteSupport;

import java.util.Date;

public abstract class AbstractNode extends Model implements SoftDeleteSupport{
    //-- Attribute
    protected long typeId;
    protected long ownerId;
    protected boolean deleted;
    protected int status;
    protected Date date;

    // Public
    public AbstractNode(){
    }
    public AbstractNode(long id){
        super(id);
    }

    //-- SoftDeleteSupport overrides
    @Override
    public final boolean isDeleted() {
        return deleted;
    }

    @Override
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }


    //-- Getter/Setter
    public final Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public final long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public final int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public final long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }
}
