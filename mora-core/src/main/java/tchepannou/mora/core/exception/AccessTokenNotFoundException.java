package tchepannou.mora.core.exception;

public class AccessTokenNotFoundException extends AuthException{
    public AccessTokenNotFoundException(String message) {
        super(message);
    }
}
