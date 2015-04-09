package tchepannou.mora.core.exception;

public class UserNotFoundException extends UserException {
    private final long userId;

    public UserNotFoundException(long userId){
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }
}
