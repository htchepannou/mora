package tchepannou.mora.core.domain;

import java.util.Date;

public abstract class LifecycleAwareModel extends Model implements SoftDeleteSupport{
    //-- Attributes
    private Date creationDate;
    private Date lastUpdate;
    private boolean deleted;

    //-- Constructor
    public LifecycleAwareModel(){

    }
    public LifecycleAwareModel(long id){
        super(id);
    }
    public LifecycleAwareModel(LifecycleAwareModel model){
        super(model);

        this.creationDate = model.getCreationDate();
        this.lastUpdate = model.getLastUpdate();
        this.deleted = model.isDeleted();
    }

    //-- Getter/Setter
    public final Date getCreationDate() {
        return creationDate;
    }

    public final void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public final boolean isDeleted() {
        return deleted;
    }

    @Override
    public final void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public final Date getLastUpdate() {
        return lastUpdate;
    }

    public final void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
