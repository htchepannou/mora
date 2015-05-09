package tchepannou.mora.core.config;

import org.junit.Test;
import tchepannou.mora.core.service.impl.*;

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

    @Test
    public void testMediaService() throws Exception {
        assertThat(config.mediaService(), instanceOf(MediaServiceImpl.class));
    }
    @Test
    public void testMediaTypeService() throws Exception {
        assertThat(config.mediaTypeService(), instanceOf(MediaTypeServiceImpl.class));
    }

    @Test
    public void testOembedService() throws Exception {
        assertThat(config.oembedService(), instanceOf(OembedServiceImpl.class));
    }

    @Test
    public void testEventService() throws Exception {
        assertThat(config.eventService(), instanceOf(EventServiceImpl.class));
    }

    @Test
    public void testEventTypeService() throws Exception {
        assertThat(config.eventTypeService(), instanceOf(EventTypeServiceImpl.class));
    }
}