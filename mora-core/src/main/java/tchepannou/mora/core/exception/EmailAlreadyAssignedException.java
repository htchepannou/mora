package tchepannou.mora.core.exception;

public class EmailAlreadyAssignedException extends UserException {
    private final String email;

    public EmailAlreadyAssignedException(String email){
        this.email = email;
    }

    public String getEmail (){
        return email;
    }
}
