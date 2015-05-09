package tchepannou.mora.core.domain;

import com.google.common.base.Preconditions;

import java.time.ZoneId;
import java.util.Date;

public class Event extends LifecycleAwareModel{
    //-- Attributes
    private long spaceId;
    private long userId;
    private long typeId;
    private String title;
    private Date startDateTime;
    private Date endDateTime;
    private boolean requiresRSVP;
    private String location;
    private String address;
    private String url;
    private String notes;
    private ZoneId timezone;

    //-- Constructor
    public Event(){
    }
    public Event (long id) {
        super(id);
    }
    public Event(long id, EventType type, Space space, User user){
        super(id);
        
        Preconditions.checkArgument(type != null, "type==null");
        Preconditions.checkArgument(type.getId()>0, "type.id<=0");

        Preconditions.checkArgument(space != null, "space==null");
        Preconditions.checkArgument(space.getId()>0, "space.id<=0");

        Preconditions.checkArgument(user != null, "user==null");
        Preconditions.checkArgument(user.getId() > 0, "user.id<=0");

        typeId = type.getId();
        spaceId = space.getId();
        userId = user.getId();
    }
    
    //-- Getter/Setter
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isRequiresRSVP() {
        return requiresRSVP;
    }

    public void setRequiresRSVP(boolean requiresRSVP) {
        this.requiresRSVP = requiresRSVP;
    }

    public long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(long spaceId) {
        this.spaceId = spaceId;
    }

    public ZoneId getTimezone() {
        return timezone;
    }

    public void setTimezone(ZoneId timezone) {
        this.timezone = timezone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }
}

