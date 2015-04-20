package tchepannou.mora.core.domain;

public class User extends LifecycleAwareModel{
    //-- Attributes
    private String username;
    private String firstName;
    private String lastName;
    private String email;


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
    }

    //-- Getter/Setter
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
}
