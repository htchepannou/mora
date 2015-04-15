package tchepannou.mora.rest.core.controller;

import org.junit.Test;
import tchepannou.mora.rest.core.exception.BadRequestException;
import tchepannou.mora.rest.core.exception.NotFoundException;
import tchepannou.mora.rest.core.exception.OperationFailedException;
import tchepannou.mora.rest.core.exception.RestException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BaseRestControllerTest {
    @Test
    public void testNotFoundException() throws Exception {
        RestException ex = new NotFoundException("foo");

        Map result = new BaseRestController().handleRestException(ex);

        Map expected = new HashMap();
        expected.put("statusCode", 404);
        expected.put("message", "foo");
        assertThat(result, equalTo(expected));
    }

    @Test
    public void testBadRequestException() throws Exception {
        RestException ex = new BadRequestException("foo");

        Map result = new BaseRestController().handleRestException(ex);

        Map expected = new HashMap();
        expected.put("statusCode", 400);
        expected.put("message", "foo");
        assertThat(result, equalTo(expected));
    }

    @Test
    public void testOperationFailedException() throws Exception {
        RestException ex = new OperationFailedException("foo");

        Map result = new BaseRestController().handleRestException(ex);

        Map expected = new HashMap();
        expected.put("statusCode", 409);
        expected.put("message", "foo");
        assertThat(result, equalTo(expected));
    }

    @Test
    public void testHandleOperation() throws Exception {
        IOException cause = new IOException("bar");

        RestException ex = mock(RestException.class);
        when(ex.getStatusCode()).thenReturn(411);
        when(ex.getMessage()).thenReturn("foo");
        when(ex.getCause()).thenReturn(cause);

        Map result = new BaseRestController().handleRestException(ex);

        Map expected = new HashMap();
        expected.put("statusCode", 411);
        expected.put("message", "foo");
        expected.put("cause", "java.io.IOException: bar");
        assertThat(result, equalTo(expected));
    }


}