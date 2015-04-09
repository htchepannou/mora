package tchepannou.mora.rest.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (value= HttpStatus.NOT_FOUND, reason="User not found")
public class UserNotFoundException extends Exception {
    private final long userId;

    public UserNotFoundException(long userId){
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }
}
