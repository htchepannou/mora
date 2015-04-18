package tchepannou.mora.insidesoccer.config;

import org.junit.Test;
import tchepannou.mora.insidesoccer.service.impl.IsAccessTokenService;
import tchepannou.mora.insidesoccer.service.impl.IsPasswordService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

public class IsAuthConfigTest {
    IsAuthConfig config = new IsAuthConfig();

    @Test
    public void testAccessTokenService() throws Exception {
        assertThat(config.accessTokenService(), instanceOf(IsAccessTokenService.class));
    }



    @Test
    public void testPasswordService() throws Exception {
        assertThat(config.passwordService(), instanceOf (IsPasswordService.class));
    }
}