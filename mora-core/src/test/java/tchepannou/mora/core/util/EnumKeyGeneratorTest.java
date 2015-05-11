package tchepannou.mora.core.util;

import org.junit.Test;
import tchepannou.mora.core.domain.Role;
import tchepannou.mora.core.service.RoleService;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

public class EnumKeyGeneratorTest {

    @Test
    public void testGenerate() throws Exception {
        Method method = RoleService.class.getMethods()[0];
        Object key = new EnumKeyGenerator().generate(Role.class, method, 1L);

        assertThat(key).isInstanceOf(EnumKey.class);
    }
}