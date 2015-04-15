package tchepannou.mora.rest.core.exception;

import org.junit.Test;

import javax.servlet.http.HttpServletResponse;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class NotFoundExceptionTest {
    @Test
    public void testStatusCode (){
        assertThat(new NotFoundException("foo").getStatusCode(), equalTo(HttpServletResponse.SC_NOT_FOUND));
    }

    @Test
    public void testMessage (){
        assertThat(new NotFoundException("foo").getMessage(), equalTo("foo"));
    }

    @Test
    public void testMessage_Long (){
        assertThat(new NotFoundException(1L).getMessage(), equalTo("1"));
    }
}