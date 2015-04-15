package tchepannou.mora.rest.core.exception;

public abstract class RestException extends RuntimeException{
    public RestException (String message){
        super(message);
    }

    public RestException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode ();
}
