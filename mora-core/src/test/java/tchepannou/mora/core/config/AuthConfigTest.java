package tchepannou.mora.core.config;

import org.junit.Test;
import tchepannou.mora.core.service.impl.AccessTokenServiceImpl;
import tchepannou.mora.core.service.impl.Md5HashService;
import tchepannou.mora.core.service.impl.PasswordServiceImpl;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

public class AuthConfigTest {
    AuthConfig config = new AuthConfig ();

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
}
