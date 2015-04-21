package tchepannou.mora.rest.post.dto;

import org.junit.Test;
import tchepannou.mora.core.domain.User;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class UserDtoTest {
    @Test
    public void testBuilder (){
        // Given
        User user = new User (1);
        user.setFirstName("Ray");
        user.setLastName("Sponsible");

        // When
        UserDto result = new UserDto.Builder().withUser(user).build();

        // Then
        assertThat(result.getId(), equalTo(user.getId()));
        assertThat(result.getName(), equalTo("Ray Sponsible"));
    }
}