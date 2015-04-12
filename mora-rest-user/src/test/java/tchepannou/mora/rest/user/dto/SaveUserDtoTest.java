package tchepannou.mora.rest.user.dto;

import org.junit.Before;
import org.junit.Test;
import tchepannou.mora.core.domain.User;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class SaveUserDtoTest {
    private SaveUserDto dto;
    private User expected;

    @Before
    public void setUp (){
        dto = new SaveUserDto();
        dto.setFirstName("Ray");
        dto.setLastName("Sponsible");
        dto.setEmail("ray.sponsible@gmail.com");

        expected = new User(1);
        expected.setFirstName("Ray");
        expected.setLastName("Sponsible");
        expected.setEmail("ray.sponsible@gmail.com");

    }

    @Test
    public void testToUser() throws Exception {
        // Given
        User user = new User (1);

        // When
        dto.toUser(user);

        // Then
        assertThat(user, equalTo(expected));
    }

    @Test
    public void testToUser_noFirstName_dontOverwriteFirstName() throws Exception {
        // Given
        User user = new User (1);
        dto.setFirstName(null);

        user.setFirstName("John");

        // When
        dto.toUser(user);

        // Then
        expected.setFirstName("John");
        assertThat(user, equalTo(expected));
    }

    @Test
    public void testToUser_noLastName_dontOverwriteLastName() throws Exception {
        // Given
        User user = new User (1);
        dto.setLastName(null);

        user.setLastName("Doe");

        // When
        dto.toUser(user);

        // Then
        expected.setLastName("Doe");
        assertThat(user, equalTo(expected));
    }


    @Test
    public void testToUser_noEmail_dontOverwriteEmail() throws Exception {
        // Given
        User user = new User (1);
        dto.setEmail(null);

        user.setEmail("john.doe@gmail.com");

        // When
        dto.toUser(user);

        // Then
        expected.setEmail("john.doe@gmail.com");
        assertThat(user, equalTo(expected));
    }
}