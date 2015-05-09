package tchepannou.mora.rest.core.dto;

import org.junit.Test;
import tchepannou.mora.core.domain.Role;

import static org.assertj.core.api.Assertions.assertThat;

public class EnumDtoTest {
    @Test
    public void testBuilder (){
        Role role = new Role (1, "foo");

        EnumDto dto = new EnumDto.Builder().withEnum(role).build();

        assertThat(dto.getId()).isEqualTo(role.getId());
        assertThat(dto.getName()).isEqualTo(role.getName());
    }

    @Test(expected = IllegalStateException.class)
    public void testBuilder_null_throwsIllegalStateException (){
        new EnumDto.Builder().build();
    }
}