package tchepannou.mora.rest.core.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import tchepannou.mora.rest.core.exception.BadRequestException;
import tchepannou.mora.rest.core.exception.NotFoundException;
import tchepannou.mora.rest.core.exception.OperationFailedException;
import tchepannou.mora.rest.core.exception.RestException;

import java.util.HashMap;
import java.util.Map;

public class BaseRestController {
    //-- Exception handlers
    @ResponseStatus (value= HttpStatus.NOT_FOUND)
    @ExceptionHandler (NotFoundException.class)
    public Map handleNotFoundException(NotFoundException ex) {
        return handleRestException(ex);
    }

    @ResponseStatus (value= HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public  Map handleBadRequestException(BadRequestException ex) {
        return handleRestException(ex);
    }

    @ResponseStatus (value= HttpStatus.CONFLICT)
    @ExceptionHandler(OperationFailedException.class)
    public  Map handleOperationFailedException(OperationFailedException ex) {
        return handleRestException(ex);
    }

    private  Map handleRestException(RestException ex) {
        Map map = new HashMap();
        map.put("statusCode", ex.getStatusCode());
        map.put("message", ex.getMessage());

        return map;
    }
}
