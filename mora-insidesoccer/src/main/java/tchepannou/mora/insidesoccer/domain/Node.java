package tchepannou.mora.insidesoccer.domain;

import tchepannou.mora.core.domain.Model;
import tchepannou.mora.core.domain.SoftDeleteSupport;

import java.util.Date;

public class Node extends Model implements SoftDeleteSupport{
    //-- Attribute
    private long typeId;
    private long ownerId;
    private long channelId;
    private boolean deleted;
    private int status;
    private Date date;

    // Public
    public Node(){
    }
    public Node(long id){
        super(id);
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

    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }
}
