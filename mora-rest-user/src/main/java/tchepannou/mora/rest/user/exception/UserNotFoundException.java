package tchepannou.mora.rest.user.exception;

public class UserNotFoundException extends Exception {
    private final long userId;

    public UserNotFoundException(long userId){
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }
}
