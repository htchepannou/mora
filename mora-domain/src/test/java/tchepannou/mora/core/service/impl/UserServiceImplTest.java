package tchepannou.mora.core.service.impl;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import tchepannou.mora.core.dao.UserDao;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.core.service.UserService;

import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest{
    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserService service = new UserServiceImpl();

    //-- Tests
    @Before
    public void setUp (){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindByEmail() throws Exception {
        // Given
        User user = new User (1);
        when(userDao.findByEmail(anyString())).thenReturn(user);

        // When
        User result = service.findByEmail("foo@gmail.com");

        // Then
        assertThat(result, equalTo(user));
    }

    @Test
    public void testFindByUsername() throws Exception {
        // Given
        User user = new User (1);
        when(userDao.findByUsername(anyString())).thenReturn(user);

        // When
        User result = service.findByUsername("foo");

        // Then
        assertThat(result, equalTo(user));
    }

    @Test
    public void testSaveNew_shouldSetLastUpdateAndCreationDate () throws Exception {
        // Given
        Date now = new Date ();
        User user = new User (1);

        User expected = new User(user);
        expected.setCreationDate(now);
        expected.setLastUpdate(now);

        // When
        service.save(user);

        // Then
        verify(userDao).save(user);

        assertThat(user, equalTo(expected));
    }

    @Test
    public void testSaveUpdate_shouldUpdateLastUpdate () throws Exception {
        // Given
        User user = new User (1);
        user.setCreationDate(DateUtils.addDays(new Date (), -1));

        User expected = new User(user);
        expected.setLastUpdate(new Date());

        // When
        service.save(user);

        // Then
        verify(userDao).save(user);

        assertThat(user, equalTo(expected));
    }

    @Test
    public void delete () throws Exception {
        // Given
        User user = new User (1);

        User expected = new User (user);
        expected.setEmail(UserServiceImpl.DELETED_PREFIX + expected.getEmail());
        expected.setUsername(UserServiceImpl.DELETED_PREFIX + expected.getUsername());
        expected.setDeleted(true);

        // When
        service.delete(user);

        // Then
        verify(userDao).save(user);

        assertThat(user, equalTo(expected));
    }
}