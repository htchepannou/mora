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
import tchepannou.mora.core.exception.EmailAlreadyAssignedException;
import tchepannou.mora.core.exception.UsernameAlreadyAssignedException;
import tchepannou.mora.core.service.UserService;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.assertThat;
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
    public void testFindById (){
        // Given
        User user = new User (1);
        when(userDao.findById(1)).thenReturn(user);

        // When
        User result = service.findById(1);

        // Then
        assertThat(result, equalTo(user));
    }

    @Test
    public void testFindById_notFound_shouldReturnNull (){
        // Given

        // When
        User result = service.findById(1);

        // Then
        assertThat(result, nullValue());
    }
    

    @Test
    public void testFindByIds () throws Exception{
        // Given
        User user1 = new User(1);
        User user2 = new User(2);
        when(userDao.findByIds(Arrays.asList(1L, 2L))).thenReturn(Arrays.asList(user1, user2));

        List<User> result = service.findByIds(Arrays.asList(1L, 2L));

        assertThat(result, equalTo(Arrays.asList(user1, user2)));
    }
    


    @Test
    public void testFindByEmail() throws Exception {
        // Given
        User user = new User (1);
        when(userDao.findByEmail("foo@gmail.com")).thenReturn(Arrays.asList(user));

        // When
        User result = service.findByEmail("foo@gmail.com");

        // Then
        assertThat(result, equalTo(user));
    }
    @Test
    public void testFindByEmail_notFound_shouldReturnNull() throws Exception {
        // Given
        User user = new User (1);
        when(userDao.findByEmail("foo@gmail.com")).thenReturn((List)Collections.emptyList());

        // When
        User result = service.findByEmail("foo@gmail.com");

        // Then
        assertThat(result, nullValue());
    }


    @Test
    public void testFindByUsername() throws Exception {
        // Given
        User user = new User (1);
        when(userDao.findByUsername("foo")).thenReturn(Arrays.asList(user));

        // When
        User result = service.findByUsername("foo");

        // Then
        assertThat(result, equalTo(user));
    }
    @Test
    public void testFindByUsername_notFound_shouldReturnNull() throws Exception {
        // Given
        when(userDao.findByUsername("foo")).thenReturn((List)Collections.emptyList());

        // When
        User result = service.findByUsername("foo");

        // Then
        assertThat(result, nullValue());
    }



    @Test
    public void testCreate () throws Exception {
        // Given
        Date now = new Date ();
        User user = new User ();

        User expected = new User(user);
        expected.setCreationDate(now);
        expected.setLastUpdate(now);

        // When
        User result = service.create(user);

        // Then
        verify(userDao).create(user);

        assertThat(result.getCreationDate(), greaterThanOrEqualTo(now));
        assertThat(result.getLastUpdate(), greaterThanOrEqualTo(now));

        expected.setLastUpdate(result.getLastUpdate());
        expected.setCreationDate(result.getCreationDate());
        assertThat(user, equalTo(expected));
    }

    @Test(expected = EmailAlreadyAssignedException.class)
    public void testCreate_EmailAlreadyAssigned_shouldThrowEmailAlreadyAssignedException () throws Exception {
        // Given
        User user = new User ();
        user.setEmail("foo.bar@gmail.com");

        User user2 = new User (1);
        user2.setEmail(user.getEmail());
        when(userDao.findByEmail(user2.getEmail())).thenReturn(Arrays.asList(user2));

        // When
        service.create(user);
    }

    @Test(expected = UsernameAlreadyAssignedException.class)
    public void testCreate_UsernameAlreadyAssigned_shouldThrowEmailAlreadyAssignedException () throws Exception {
        // Given
        User user = new User ();
        user.setUsername("foo.bar");

        User user2 = new User (1);
        user2.setUsername(user.getUsername());
        when(userDao.findByUsername(user2.getUsername())).thenReturn(Arrays.asList(user2));

        // When
        service.create(user);
    }



    @Test
    public void testUpdate () throws Exception {
        // Given
        Date now = new Date ();
        User user = new User (1);
        user.setCreationDate(DateUtils.addDays(new Date (), -1));

        User expected = new User(user);
        expected.setLastUpdate(new Date());

        // When
        User result = service.update(user);

        // Then
        verify(userDao).update(user);

        assertThat(result.getLastUpdate(), greaterThanOrEqualTo(now));

        expected.setLastUpdate(result.getLastUpdate());
        assertThat(user, equalTo(expected));
    }

    @Test(expected = EmailAlreadyAssignedException.class)
    public void testUpdate_EmailAlreadyAssigned_shouldThrowEmailAlreadyAssignedException () throws Exception {
        // Given
        User user = new User (1);
        user.setEmail("foo.bar@gmail.com");

        User user2 = new User (2);
        user2.setEmail(user.getEmail());
        when(userDao.findByEmail(user2.getEmail())).thenReturn(Arrays.asList(user2));

        // When
        service.update(user);
    }


    @Test(expected = UsernameAlreadyAssignedException.class)
    public void testUpdate_UsernameAlreadyAssigned_shouldThrowEmailAlreadyAssignedException () throws Exception {
        // Given
        User user = new User (1);
        user.setUsername("foo.bar");

        User user2 = new User (2);
        user2.setUsername(user.getUsername());
        when(userDao.findByUsername(user2.getUsername())).thenReturn(Arrays.asList(user2));

        // When
        service.update(user);
    }


    @Test
    public void delete () throws Exception {
        // Given
        User user = new User (1);

        // When
        service.delete(user);

        // Then
        verify(userDao).delete(user);
    }
}