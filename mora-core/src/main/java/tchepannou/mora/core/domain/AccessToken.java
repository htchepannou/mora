package tchepannou.mora.core.domain;

import com.google.common.base.Preconditions;

import java.util.Date;

public class AccessToken extends Model{
    //-- Attributes
    private long userId;
    private String value;
    private Date creationDate = new Date ();
    private Date expiryDate;

    //-- Constructor
    public AccessToken(){
    }
    public AccessToken(User user){
        this(0, user);
    }
    public AccessToken(long id, User user){
        super(id);

        Preconditions.checkArgument(user != null, "user==null");
        Preconditions.checkArgument(user.getId() > 0, "user.id==0");

        this.userId = user.getId();
    }
    public AccessToken(AccessToken token){
        super(token);

        this.userId = token.getUserId();
        this.value = token.getValue();
        this.creationDate = token.getCreationDate();
        this.expiryDate = token.getExpiryDate();
    }


    //-- Public
    public void expire (){
        this.expiryDate = new Date (System.currentTimeMillis()-1000);
    }

    //-- Getter/Setter
    public boolean isExpired (){
        return this.expiryDate != null && this.expiryDate.getTime() <= System.currentTimeMillis();
    }

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
