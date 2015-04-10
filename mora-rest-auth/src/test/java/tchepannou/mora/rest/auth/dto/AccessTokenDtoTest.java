package tchepannou.mora.rest.auth.dto;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import tchepannou.mora.core.domain.AccessToken;
import tchepannou.mora.core.domain.User;

import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.util.MatcherAssertionErrors.assertThat;

public class AccessTokenDtoTest {
    @Test
    public void testBuilder (){
        // Given
        AccessToken token = new AccessToken(1, new User(1));
        token.setExpiryDate(DateUtils.addDays(new Date(), -1));
        token.setCreationDate(DateUtils.addDays(new Date(), -1));
        token.setValue("12345");

        // When
        AccessTokenDto result = new AccessTokenDto.Builder().withAccessToken(token).build();

        // Then
        assertThat(result.getExpiryDate(), equalTo(token.getExpiryDate()));
        assertThat(result.getCreationDate(), equalTo(token.getCreationDate()));
        assertThat(result.getValue(), equalTo(token.getValue()));
    }

    @Test(expected = IllegalStateException.class)
    public void testBuilder_noToken_throwsIllegalStateException () {
        new AccessTokenDto.Builder().build();
    }
}