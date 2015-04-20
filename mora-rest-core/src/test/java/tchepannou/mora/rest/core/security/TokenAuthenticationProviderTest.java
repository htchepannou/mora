package tchepannou.mora.rest.core.security;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;
import tchepannou.mora.core.domain.AccessToken;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.core.service.AccessTokenService;
import tchepannou.mora.core.service.UserService;

import java.util.Date;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TokenAuthenticationProviderTest {
    private User user;
    private AccessToken token;
    private Authentication auth;

    @Mock
    private AccessTokenService accessTokenService;

    @Mock
    private UserService userService;

    @InjectMocks
    private TokenAuthenticationProvider provider;

    //-- Tests
    @Before
    public void setUp (){
        provider = new TokenAuthenticationProvider(accessTokenService, userService);

        user = new User (1);

        token = new AccessToken(100, user);
        token.setValue("1234");
        token.setExpiryDate(DateUtils.addDays(new Date(), 1));

        auth = new TokenAuthentication(token.getValue());
    }

    @Test
    public void testAuthenticate() throws Exception {
        // Given
        when(userService.findById(user.getId())).thenReturn(user);
        when(accessTokenService.findByValue(token.getValue())).thenReturn(token);

        // When
        Authentication xauth = provider.authenticate(auth);

        // Then
        assertThat(xauth.getName(), equalTo(String.valueOf(token.getValue())));
        assertThat(xauth.getCredentials(), nullValue());
        assertThat(xauth.isAuthenticated(), equalTo(true));
    }

    @Test(expected = BadCredentialsException.class)
    public void testAuthenticate_AccessTokenNotFound_shouldThrowBadCredentialsException() throws Exception {
        // Given
        when(userService.findById(user.getId())).thenReturn(user);

        // When
        provider.authenticate(auth);
    }

    @Test(expected = CredentialsExpiredException.class)
    public void testAuthenticate_AccessTokenExpired_shouldThrowBadCredentialsException() throws Exception {
        // Given
        when(userService.findById(user.getId())).thenReturn(user);

        token.setExpiryDate(DateUtils.addDays(new Date(), -1));
        when(accessTokenService.findByValue(token.getValue())).thenReturn(token);

        // When
        provider.authenticate(auth);
    }


    @Test(expected = PreAuthenticatedCredentialsNotFoundException.class)
    public void testAuthenticate_UserNotFound_shouldThrowPreAuthenticatedCredentialsNotFoundException() throws Exception {
        // Given
        when(accessTokenService.findByValue(token.getValue())).thenReturn(token);

        // When
        provider.authenticate(auth);
    }

    @Test
    public void testSupports() throws Exception {
        assertThat(provider.supports(TokenAuthentication.class), equalTo(true));
    }
}