package tchepannou.mora.core.domain;

import com.google.common.base.Preconditions;

import java.util.Date;

public class Password extends Model {
    //-- Attributes
    private long userId;
    private String value;
    private Date creationDate = new Date ();
    private Date lastUpdate;

    //-- Public
    public Password (){
    }
    public Password (User user){
        this(0, user);

        this.userId = user.getId();
    }
    public Password (long id, User user){
        super(id);

        Preconditions.checkArgument(user != null, "user==null");
        Preconditions.checkArgument(user.getId()>0, "user.id==0");

        this.userId = user.getId();
    }
    public Password(Password password){
        super(password);

        this.userId = password.getUserId();
        this.value = password.getValue();
        this.lastUpdate = password.getLastUpdate();
        this.creationDate = password.getCreationDate();
    }


    //-- Getter/Setter
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
