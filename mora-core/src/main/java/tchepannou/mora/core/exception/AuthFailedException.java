package tchepannou.mora.core.exception;

public class AuthFailedException extends AccessTokenException {
    public AuthFailedException(String message) {
        super(message);
    }
}
