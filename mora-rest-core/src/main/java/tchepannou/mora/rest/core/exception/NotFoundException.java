package tchepannou.mora.rest.core.exception;

import javax.servlet.http.HttpServletResponse;

public class NotFoundException extends RestException {
    public NotFoundException(String msg){
        super(msg);
    }
    public NotFoundException(long id){
        super(String.valueOf(id));
    }

    @Override
    public int getStatusCode() {
        return HttpServletResponse.SC_NOT_FOUND;
    }
}
