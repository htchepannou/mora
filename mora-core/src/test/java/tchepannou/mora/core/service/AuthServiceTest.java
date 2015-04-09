package tchepannou.mora.core.service;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tchepannou.mora.core.dao.AccessTokenDao;
import tchepannou.mora.core.domain.AccessToken;
import tchepannou.mora.core.domain.Password;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.core.exception.AuthException;
import tchepannou.mora.core.exception.NoPasswordException;
import tchepannou.mora.core.service.impl.AuthServiceImpl;

import java.util.Arrays;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class AuthServiceTest {
    @Mock
    private AccessTokenDao accessTokenDao;

    @Mock
    private UserService userService;

    @Mock
    private PasswordService passwordService;

    @Mock
    private HashService hashService;

    @InjectMocks
    private AuthServiceImpl service = new AuthServiceImpl();

    @Test
    public void testFindByKey() throws Exception {
        // Given
        AccessToken token = new AccessToken();
        when(accessTokenDao.findByValue("toto")).thenReturn(token);

        // When/Then
        assertThat(service.findByKey("toto"), equalTo(token));
    }

    @Test
    public void testLogout() throws Exception {
        // Given
        AccessToken token = mock(AccessToken.class);
        when(accessTokenDao.findByValue("toto")).thenReturn(token);

        // When
        service.logout("toto");

        // Then
        verify(token).expire();
        verify(accessTokenDao).update(token);
    }


    @Test
    public void testAuthenticateByEmail() throws Exception {
        // Given
        Date now = new Date();

        User user = new User (1);
        when(userService.findByEmail("toto@gmail.com")).thenReturn(user);

        Password password = new Password(user);
        password.setValue("_secret_");
        when(passwordService.findByUser(user.getId())).thenReturn(password);
        when(passwordService.encrypt("secret")).thenReturn("_secret_");

        when(hashService.generate(anyString())).thenReturn("_key_");

        AccessToken tok1 = mock(AccessToken.class);
        AccessToken tok2 = mock(AccessToken.class);
        AccessToken tok3 = mock(AccessToken.class);
        when (accessTokenDao.findByUserByExpired(user.getId(), false)).thenReturn(Arrays.asList(tok1, tok2, tok3));

        service.setTokenTTLMinutes(15);

        // When
        AccessToken result = service.authenticate("toto@gmail.com", "secret");

        // Then
        verify(accessTokenDao).create(result);  // Token is saved

        verify(tok1).expire();      // Previous session are expired
        verify(tok2).expire();
        verify(tok3).expire();
        verify(accessTokenDao).update(Arrays.asList(tok1, tok2, tok3));

        assertThat(result.getCreationDate(), greaterThanOrEqualTo(now));
        assertThat(DateUtils.addMinutes(result.getCreationDate(), 15), greaterThanOrEqualTo(result.getExpiryDate()));

        AccessToken expected = new AccessToken(user);
        expected.setValue("_key_");
        expected.setCreationDate(result.getCreationDate());
        expected.setExpiryDate(result.getExpiryDate());
        assertThat(result, equalTo(expected));
    }

    @Test(expected = AuthException.class)
    public void testAuthenticateByEmail_notFound_shouldThrowAuthException() throws Exception {
        // Given
        User user = new User (1);

        Password password = new Password(user);
        password.setValue("_secret_");
        when(passwordService.findByUser(user.getId())).thenReturn(password);
        when(passwordService.encrypt("secret")).thenReturn("_secret_");

        when(hashService.generate(anyString())).thenReturn("_key_");

        AccessToken tok1 = mock(AccessToken.class);
        AccessToken tok2 = mock(AccessToken.class);
        AccessToken tok3 = mock(AccessToken.class);
        when (accessTokenDao.findByUserByExpired(user.getId(), false)).thenReturn(Arrays.asList(tok1, tok2, tok3));

        service.setTokenTTLMinutes(15);

        // When
        service.authenticate("toto@gmail.com", "secret");

        // Then
    }



    @Test
    public void testAuthenticateByUsername() throws Exception {
        // Given
        Date now = new Date();

        User user = new User (1);
        when(userService.findByUsername("toto")).thenReturn(user);

        Password password = new Password(user);
        password.setValue("_secret_");
        when(passwordService.findByUser(user.getId())).thenReturn(password);
        when(passwordService.encrypt("secret")).thenReturn("_secret_");

        when(hashService.generate(anyString())).thenReturn("_key_");

        AccessToken tok1 = mock(AccessToken.class);
        AccessToken tok2 = mock(AccessToken.class);
        AccessToken tok3 = mock(AccessToken.class);
        when (accessTokenDao.findByUserByExpired(user.getId(), false)).thenReturn(Arrays.asList(tok1, tok2, tok3));

        service.setTokenTTLMinutes(15);

        // When
        AccessToken result = service.authenticate("toto", "secret");

        // Then
        verify(accessTokenDao).create(result);  // Token is saved

        verify(tok1).expire();      // Previous session are expired
        verify(tok2).expire();
        verify(tok3).expire();
        verify(accessTokenDao).update(Arrays.asList(tok1, tok2, tok3));

        assertThat(result.getCreationDate(), greaterThanOrEqualTo(now));
        assertThat(DateUtils.addMinutes(result.getCreationDate(), 15), greaterThanOrEqualTo(result.getExpiryDate()));

        AccessToken expected = new AccessToken(user);
        expected.setValue("_key_");
        expected.setCreationDate(result.getCreationDate());
        expected.setExpiryDate(result.getExpiryDate());
        assertThat(result, equalTo(expected));
    }

    @Test(expected = AuthException.class)
    public void testAuthenticateByUsername_notFound_shouldThrowAuthException() throws Exception {
        // Given
        User user = new User (1);

        Password password = new Password(user);
        password.setValue("_secret_");
        when(passwordService.findByUser(user.getId())).thenReturn(password);
        when(passwordService.encrypt("secret")).thenReturn("_secret_");

        when(hashService.generate(anyString())).thenReturn("_key_");

        AccessToken tok1 = mock(AccessToken.class);
        AccessToken tok2 = mock(AccessToken.class);
        AccessToken tok3 = mock(AccessToken.class);
        when (accessTokenDao.findByUserByExpired(user.getId(), false)).thenReturn(Arrays.asList(tok1, tok2, tok3));

        service.setTokenTTLMinutes(15);

        // When
        service.authenticate("toto", "secret");
    }


    @Test(expected = NoPasswordException.class)
    public void testAuthenticate_noPassword_shouldThrowNoPasswordException() throws Exception {
        // Given
        User user = new User (1);
        when(userService.findByUsername("toto")).thenReturn(user);

        when(hashService.generate(anyString())).thenReturn("_key_");

        AccessToken tok1 = mock(AccessToken.class);
        AccessToken tok2 = mock(AccessToken.class);
        AccessToken tok3 = mock(AccessToken.class);
        when (accessTokenDao.findByUserByExpired(user.getId(), false)).thenReturn(Arrays.asList(tok1, tok2, tok3));

        service.setTokenTTLMinutes(15);

        // When
        service.authenticate("toto", "secret");
    }

    @Test(expected = AuthException.class)
    public void testAuthenticate_BadPassword_shouldThrowAuthExcepion() throws Exception {
        // Given
        User user = new User (1);
        when(userService.findByUsername("toto")).thenReturn(user);

        Password password = new Password(user);
        password.setValue("_secret_");
        when(passwordService.findByUser(user.getId())).thenReturn(password);
        when(passwordService.encrypt("bad_password")).thenReturn("_bad_password_");

        when(hashService.generate(anyString())).thenReturn("_key_");

        AccessToken tok1 = mock(AccessToken.class);
        AccessToken tok2 = mock(AccessToken.class);
        AccessToken tok3 = mock(AccessToken.class);
        when (accessTokenDao.findByUserByExpired(user.getId(), false)).thenReturn(Arrays.asList(tok1, tok2, tok3));

        service.setTokenTTLMinutes(15);

        // When
        service.authenticate("toto", "bad_password");

        // Then
    }
}