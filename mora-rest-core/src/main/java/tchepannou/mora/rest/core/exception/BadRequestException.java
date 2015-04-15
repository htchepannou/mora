package tchepannou.mora.rest.core.exception;

import javax.servlet.http.HttpServletResponse;

public class BadRequestException extends RestException{
    public BadRequestException(String message) {
        super(message);
    }

    @Override
    public int getStatusCode() {
        return HttpServletResponse.SC_BAD_REQUEST;
    }
}
