package tchepannou.mora.core.domain;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.util.MatcherAssertionErrors.assertThat;

public class AccessTokenTest {
    @Test
    public void testIsExpired_Tomorrow_shouldReturnFalse() throws Exception {
        // Given
        AccessToken token = new AccessToken ();
        token.setExpiryDate(DateUtils.addDays(new Date(), 1));

        // When
        boolean result = token.isExpired();

        // Then
        assertThat(result, is(false));
    }

    @Test
    public void testIsExpired_Yesterday_shouldReturnTrue() throws Exception {
        // Given
        AccessToken token = new AccessToken ();
        token.setExpiryDate(DateUtils.addDays(new Date(), -1));

        // When
        boolean result = token.isExpired();

        // Then
        assertThat(result, is(true));
    }

    @Test
    public void testIsExpired_Now_shouldReturnTrue() throws Exception {
        // Given
        AccessToken token = new AccessToken ();
        token.setExpiryDate(new Date());

        // When
        boolean result = token.isExpired();

        // Then
        assertThat(result, is(true));
    }

    @Test
    public void testExpire() throws Exception {
        // Given
        Date now = new Date ();
        AccessToken token = new AccessToken();

        // When
        token.expire();

        // Then
        assertThat(Math.abs(token.getExpiryDate().getTime()/1000 - now.getTime()/1000), equalTo(1L));
    }
}