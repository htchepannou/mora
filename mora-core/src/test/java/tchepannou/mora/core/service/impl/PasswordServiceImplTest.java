package tchepannou.mora.core.service.impl;


import org.apache.commons.lang.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tchepannou.mora.core.dao.PasswordDao;
import tchepannou.mora.core.domain.Password;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.core.service.HashService;
import tchepannou.mora.core.service.PasswordService;

import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PasswordServiceImplTest{
    @Mock
    private PasswordDao passwordDao;

    @Mock
    private HashService hashService;

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
        when(hashService.generate(password)).thenReturn("___encrypted__");

        // Then
        String result = service.encrypt(password);

        // Then
        assertThat(result, equalTo("___encrypted__"));
    }

    @Test
    public void testCreate () throws Exception {
        // Given
        Date now = new Date();
        Password password = new Password(new User(1));
        password.setValue("_secret_");

        Password expected = new Password (password);
        expected.setCreationDate(now);
        expected.setLastUpdate(now);

        // When
        service.create(password);

        // Then
        verify(passwordDao).create(password);

        assertThat(password.getCreationDate(), greaterThanOrEqualTo(now));
        assertThat(password.getLastUpdate(), greaterThanOrEqualTo(now));

        expected.setLastUpdate(password.getLastUpdate());
        expected.setCreationDate(password.getCreationDate());
        assertThat(password, equalTo(expected));
    }


    @Test
    public void testUpdate () throws Exception {
        // Given
        Date now = new Date();
        Password password = new Password(1, new User(1));
        password.setCreationDate(DateUtils.addDays(now, -1));
        password.setValue("_secret_");

        Password expected = new Password (password);
        expected.setLastUpdate(now);

        // When
        service.update(password);

        // Then
        verify(passwordDao).update(password);

        assertThat(password.getLastUpdate(), greaterThanOrEqualTo(now));

        expected.setLastUpdate(password.getLastUpdate());
        assertThat(password, equalTo(expected));
    }
}