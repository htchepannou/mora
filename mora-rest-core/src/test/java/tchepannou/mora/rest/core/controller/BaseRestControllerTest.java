package tchepannou.mora.rest.core.controller;

import org.junit.Test;
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