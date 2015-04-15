package tchepannou.mora.rest.core.exception;

import javax.servlet.http.HttpServletResponse;

public class OperationFailedException extends RestException {
    public OperationFailedException(String msg){
        super(msg);
    }
    public OperationFailedException(String msg, Throwable e){
        super(msg, e);
    }

    @Override
    public int getStatusCode() {
        return HttpServletResponse.SC_CONFLICT;
    }
}
