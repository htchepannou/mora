package tchepannou.mora.core.domain;

import com.google.common.base.Preconditions;

import java.util.Date;

public class AccessToken extends Model{
    //-- Attributes
    private long userId;
    private Date creationDate = new Date ();
    private Date expiryDate;
    private boolean expired;

    //-- Constructor
    public AccessToken(){
    }
    public AccessToken(User user){
        Preconditions.checkArgument(user != null, "user==null");
        Preconditions.checkArgument(user.getId() > 0, "user.id==0");

        this.userId = user.getId();
    }


    //-- Getter/Setter
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}
