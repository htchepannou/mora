package tchepannou.mora.core.service.impl;


import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tchepannou.mora.core.dao.PasswordDao;
import tchepannou.mora.core.domain.Password;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.core.service.PasswordService;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class PasswordServiceImplTest{
    @Mock
    private PasswordDao passwordDao;

    @InjectMocks
    PasswordService service = new PasswordServiceImpl();


    //-- Test
    @Before
    public void setUp (){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindByUser () {
        // Given
        long userId = 1;
        Password password = new Password(1, new User(userId));
        when(passwordDao.findByUser(userId)).thenReturn(password);

        // When
        Password result = service.findByUser(userId);

        // Then
        assertThat(result, equalTo(password));
    }

    @Test
    public void testEncrypt() throws Exception {
        // Given
        String password = "sample password";

        // Then
        String result = service.encrypt(password);

        // Then
        assertThat(result, not(equalTo(password)));
    }

    @Test
    public void testEncryptNull_shouldReturnNull() throws Exception {
        // Given
        String password = null;

        // Then
        String result = service.encrypt(password);

        // Then
        assertThat(result, nullValue());
    }


    @Test
    public void testMatches() throws Exception {
        // Given
        String clear = "_password_";
        Password pwd = new Password();
        pwd.setValue(service.encrypt(clear));

        // Then
        boolean result = service.matches(pwd, clear);

        // Then
        assertThat(result, equalTo(true));
    }

    @Test
    public void testMatchesNull_shouldReturnFalse() throws Exception {
        // Given
        String clear = null;
        Password pwd = new Password();
        pwd.setValue(null);

        // Then
        boolean result = service.matches(pwd, clear);

        // Then
        assertThat(result, equalTo(false));
    }

    @Test
    public void testMathesCaseSensitive (){
        // Given
        String clear = "_password_";
        Password pwd = new Password();
        pwd.setValue(service.encrypt(clear));

        // Then
        boolean result = service.matches(pwd, "_Password_");

        // Then
        assertThat(result, equalTo(false));
    }
}