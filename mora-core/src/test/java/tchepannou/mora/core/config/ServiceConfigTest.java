package tchepannou.mora.core.config;

import org.junit.Test;
import tchepannou.mora.core.service.impl.AccessTokenServiceImpl;
import tchepannou.mora.core.service.impl.Md5HashService;
import tchepannou.mora.core.service.impl.MemberServiceImpl;
import tchepannou.mora.core.service.impl.PasswordServiceImpl;
import tchepannou.mora.core.service.impl.RoleServiceImpl;
import tchepannou.mora.core.service.impl.SpaceServiceImpl;
import tchepannou.mora.core.service.impl.SpaceTypeServiceImpl;
import tchepannou.mora.core.service.impl.UserServiceImpl;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

public class ServiceConfigTest {
    ServiceConfig config = new ServiceConfig();

    @Test
    public void testUserService() throws Exception {
        assertThat(config.userService(), instanceOf(UserServiceImpl.class));
    }

    @Test
    public void testPasswordService() throws Exception {
        assertThat(config.passwordService(), instanceOf(PasswordServiceImpl.class));
    }

    @Test
    public void testHashService() throws Exception {
        assertThat(config.hashService(), instanceOf(Md5HashService.class));
    }

    @Test
    public void testAccessTokenService() throws Exception {
        assertThat(config.accessTokenService(), instanceOf(AccessTokenServiceImpl.class));
    }

    @Test
    public void testRoleService() throws Exception {
        assertThat(config.roleService(), instanceOf(RoleServiceImpl.class));
    }

    @Test
    public void testSpaceService() throws Exception {
        assertThat(config.spaceService(), instanceOf(SpaceServiceImpl.class));
    }

    @Test
    public void testSpaceTypeService() throws Exception {
        assertThat(config.spaceTypeService(), instanceOf(SpaceTypeServiceImpl.class));
    }

    @Test
    public void tesMemberService() throws Exception {
        assertThat(config.memberService(), instanceOf(MemberServiceImpl.class));
    }
}