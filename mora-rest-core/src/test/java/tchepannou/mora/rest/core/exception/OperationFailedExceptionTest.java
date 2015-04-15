package tchepannou.mora.rest.core.exception;

import org.junit.Test;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class OperationFailedExceptionTest {
    @Test
    public void testStatusCode (){
        assertThat(new OperationFailedException("foo").getStatusCode(), equalTo(HttpServletResponse.SC_CONFLICT));
    }

    @Test
    public void testMessage (){
        assertThat(new OperationFailedException("foo").getMessage(), equalTo("foo"));
    }

    @Test
    public void testCause (){
        Throwable cause = new IOException();

        assertThat(new OperationFailedException("foo", cause).getCause(), equalTo(cause));
    }
}