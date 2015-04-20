package tchepannou.mora.core.config;

import org.junit.Test;
import tchepannou.mora.core.service.impl.MemberServiceImpl;
import tchepannou.mora.core.service.impl.PostServiceImpl;
import tchepannou.mora.core.service.impl.RoleServiceImpl;
import tchepannou.mora.core.service.impl.SpaceServiceImpl;
import tchepannou.mora.core.service.impl.SpaceTypeServiceImpl;
import tchepannou.mora.core.service.impl.UrlFetchServiceImpl;
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

    @Test
    public void tesPostService() throws Exception {
        assertThat(config.postService(), instanceOf(PostServiceImpl.class));
    }

    @Test
    public void testUrlFetchService() throws Exception {
        assertThat(config.urlFetchService(), instanceOf(UrlFetchServiceImpl.class));
    }
}