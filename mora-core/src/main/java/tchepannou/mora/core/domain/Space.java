package tchepannou.mora.core.domain;

import com.google.common.base.Preconditions;

import java.util.Date;

public class Space extends Model{
    //-- Attributes
    private long typeId;
    private long userId;
    private String name;
    private String description;
    private String logoUrl;
    private boolean deleted;
    private Date creationDate;
    private Date lastUpdate;


    //-- Constructor
    public Space (){

    }

    public Space (long id, SpaceType type, User user){
        super(id);

        Preconditions.checkArgument(type != null, "type == null");
        Preconditions.checkArgument(user != null, "user == null");

        this.typeId = type.getId();
        this.userId = user.getId();
    }

    public Space (Space space){
        super(space);

        this.typeId = space.getTypeId();
        this.userId = space.getUserId();
        this.name = space.getName();
        this.description = space.getDescription();
        this.deleted = space.isDeleted();
        this.creationDate = space.getCreationDate();
        this.lastUpdate = space.getLastUpdate();
    }


    //-- Getter/Setter
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
