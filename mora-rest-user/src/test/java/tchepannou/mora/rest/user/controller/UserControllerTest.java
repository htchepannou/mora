package tchepannou.mora.rest.user.controller;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import tchepannou.mora.core.domain.Password;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.core.service.PasswordService;
import tchepannou.mora.core.service.UserService;
import tchepannou.mora.rest.user.dto.CreateUserDto;
import tchepannou.mora.rest.user.dto.SaveUserDto;
import tchepannou.mora.rest.user.dto.UserDto;
import tchepannou.mora.rest.user.exception.UserNotFoundException;

import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest{
    //-- Attributes
    private static long uid = System.currentTimeMillis();

    @Mock
    private UserService userService;

    @Mock
    private PasswordService passwordService;

    @InjectMocks
    private UserController controller = new UserController();

    //-- Private
    @Before
    public void setUp (){
        doAnswer(saveUser()).when(userService).save(any(User.class));
        doAnswer(savePassword()).when(passwordService).save(any(Password.class));
    }

    @Test
    public void testGet() throws Exception {
        // Given
        User user = createUser(1);
        when (userService.findById(user.getId())).thenReturn(user);

        UserDto expected = new UserDto.Builder().withUser(user).build();

        // When
        UserDto result = controller.get(user.getId());

        // Then
        assertThat(result, equalTo(expected));
    }

    @Test(expected = UserNotFoundException.class)
    public void testGetUserNotFound_shouldThrowUserNotFoundException() throws Exception {
        // Given

        // When
        controller.get(1);
    }

    @Test
    public void testCreate() throws Exception {
        // Given
        CreateUserDto req = new CreateUserDto();
        req.setEmail("ray.sponsible@gmail.com");
        req.setUsername("ray.sponsible");
        req.setPassword("secret");
        req.setFirstName("Ray");
        req.setLastName("Sponsible");

        when(passwordService.encrypt("secret")).thenReturn("_secret_");

        // When
        UserDto result = controller.create(req);

        // Then
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userService).save(userCaptor.capture());
        User user = userCaptor.getValue();
        assertThat(user.getFirstName(), equalTo(req.getFirstName()));
        assertThat(user.getLastName(), equalTo(req.getLastName()));
        assertThat(user.getEmail(), equalTo(req.getEmail()));
        assertThat(user.getUsername(), equalTo(req.getUsername()));

        ArgumentCaptor<Password> passwordCaptor = ArgumentCaptor.forClass(Password.class);
        verify(passwordService).save(passwordCaptor.capture());
        Password password = passwordCaptor.getValue();
        assertThat(password.getUserId(), equalTo(user.getId()));
        assertThat(password.getValue(), equalTo("_secret_"));

        UserDto expectedResult = new UserDto.Builder().withUser(user).build();
        assertThat(result, equalTo(expectedResult));
    }

    @Test
    public void testUpdate() throws Exception {
        // Given
        User user = createUser(1);
        when (userService.findById(1)).thenReturn(user);

        SaveUserDto req = new SaveUserDto();
        req.setEmail("ray.sponsible@gmail.com");
        req.setFirstName("Ray");
        req.setLastName("Sponsible");

        User expected = new User(user);
        expected.setEmail(req.getEmail());
        expected.setFirstName(req.getFirstName());
        expected.setLastName(req.getLastName());
        expected.setLastUpdate(new Date ());

        // When
        UserDto result = controller.update(1, req);

        // Then
        verify(userService).save(user);
        assertThat(user, equalTo(expected));

        assertThat(result, equalTo(new UserDto.Builder().withUser(expected).build()));
    }

    @Test(expected = UserNotFoundException.class)
    public void testUpdateUserNotFound_shouldThrowUserNotFoundException() throws Exception {
        // Given
        SaveUserDto req = new SaveUserDto();
        req.setEmail("ray.sponsible@gmail.com");
        req.setFirstName("Ray");
        req.setLastName("Sponsible");

        // When
        controller.update(1, req);
    }

    @Test
    public void testDelete() throws Exception {
        // Given
        User user = createUser(1);
        when (userService.findById(1)).thenReturn(user);

        // When
        controller.delete(1);

        // Then
        verify(userService).delete(user);
    }

    @Test
    public void testDeleteUserNotFound() throws Exception {
        // Given

        // When
        controller.delete(1);

        // Then
        verify(userService, never()).delete(any(User.class));
    }


    //-- Private
    private User createUser (long id){
        User result = new User (id);
        result.setEmail("ray" + id + "@gmail.com");
        result.setFirstName("Ray");
        result.setLastName("Sponsible");
        result.setCreationDate(DateUtils.addDays(new Date(), -1));
        result.setLastUpdate(new Date ());
        result.setUsername("ray.sponsible" + id);
        return result;
    }

    private Answer saveUser (){
        return new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                User user = (User)invocationOnMock.getArguments()[0];
                if (user.isTransient()) {
                    user.setId(++uid);
                }
                return null;
            }
        };
    }

    private Answer savePassword (){
        return new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Password password = (Password)invocationOnMock.getArguments()[0];
                if (password.isTransient()) {
                    password.setId(++uid);
                }
                return null;
            }
        };
    }
}