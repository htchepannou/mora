package tchepannou.mora.core.domain;

import java.util.Date;

public class User extends Model {
    //-- Attributes
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Date creationDate = new Date ();
    private Date lastUpdate;
    private boolean deleted;


    //-- Public
    public User (){
    }
    public User (long id){
        super(id);
    }
    public User (User user){
        super(user);
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.creationDate = user.getCreationDate();
        this.lastUpdate = user.getLastUpdate();
        this.deleted = user.isDeleted();
    }
    //-- Getter/Setter
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
