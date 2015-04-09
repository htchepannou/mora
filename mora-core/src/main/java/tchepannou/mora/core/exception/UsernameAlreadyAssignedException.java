package tchepannou.mora.core.exception;

public class UsernameAlreadyAssignedException extends UserException {
    private final String username;

    public UsernameAlreadyAssignedException(String email){
        this.username = email;
    }

    public String getUsername(){
        return username;
    }
}
