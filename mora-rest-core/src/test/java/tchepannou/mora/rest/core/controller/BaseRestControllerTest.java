package tchepannou.mora.rest.core.controller;

import org.junit.Test;
import tchepannou.mora.rest.core.exception.BadRequestException;
import tchepannou.mora.rest.core.exception.NotFoundException;
import tchepannou.mora.rest.core.exception.OperationFailedException;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class BaseRestControllerTest {

    @Test
    public void testHandleNotFoundException() throws Exception {
        Map result = new BaseRestController().handleNotFoundException(new NotFoundException("foo"));

        Map expected = new HashMap();
        expected.put("statusCode", 404);
        expected.put("message", "foo");
        assertThat(result, equalTo(expected));
    }

    @Test
    public void testHandleBadRequestException() throws Exception {
        Map result = new BaseRestController().handleBadRequestException(new BadRequestException("foo"));

        Map expected = new HashMap();
        expected.put("statusCode", 400);
        expected.put("message", "foo");
        assertThat(result, equalTo(expected));
    }


    @Test
    public void testHandleOperationFailedException() throws Exception {
        Map result = new BaseRestController().handleOperationFailedException(new OperationFailedException("foo"));

        Map expected = new HashMap();
        expected.put("statusCode", 409);
        expected.put("message", "foo");
        assertThat(result, equalTo(expected));
    }
}