package tchepannou.mora.core.exception;

public class NoPasswordException extends AuthException{
    public NoPasswordException(String message) {
        super(message);
    }
}
