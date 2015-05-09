package tchepannou.mora.rest.security.dto;

import org.junit.Test;
import tchepannou.mora.core.domain.User;

import java.util.Date;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class UserDtoTest {
    @Test
    public void testBuilder (){
        // Given
        User user = new User (1);
        user.setFirstName("Ray");
        user.setLastName("Sponsible");
        user.setEmail("ray.sponsible@gmail.com");
        user.setUsername("htchepannou");
        user.setCreationDate(new Date());
        user.setLastUpdate(new Date());
        user.setDeleted(false);

        // When
        UserDto result = new UserDto.Builder().withUser(user).build();

        // Then
        assertThat(result.getId(), equalTo(user.getId()));
        assertThat(result.getName(), equalTo("Ray Sponsible"));
        assertThat(result.getUsername(), equalTo("htchepannou"));
        assertThat(result.getFirstName(), equalTo("Ray"));
        assertThat(result.getLastName(), equalTo("Sponsible"));
        assertThat(result.getCreationDate(), equalTo(user.getCreationDate()));
        assertThat(result.getLastUpdate(), equalTo(user.getLastUpdate()));

    }

    @Test(expected = IllegalStateException.class)
    public void testBuilder_noUser_throwsIllegalStateException (){
        new UserDto.Builder().build();

    }

}