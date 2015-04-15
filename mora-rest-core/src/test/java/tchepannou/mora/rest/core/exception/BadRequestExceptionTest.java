package tchepannou.mora.rest.core.exception;

import org.junit.Test;

import javax.servlet.http.HttpServletResponse;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class BadRequestExceptionTest {
    @Test
    public void testStatusCode (){
        assertThat(new BadRequestException("foo").getStatusCode(), equalTo(HttpServletResponse.SC_BAD_REQUEST));
    }

    @Test
    public void testMessage (){
        assertThat(new BadRequestException("foo").getMessage(), equalTo("foo"));
    }
}