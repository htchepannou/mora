package tchepannou.mora.insidesoccer.config;

import org.junit.Test;
import tchepannou.mora.insidesoccer.dao.IsAccessTokenDao;
import tchepannou.mora.insidesoccer.dao.IsRoleDao;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

public class IsDaoConfigTest {
    IsDaoConfig config = new IsDaoConfig();

    @Test
    public void testRoleDao() {
        assertThat(config.roleDao(), instanceOf(IsRoleDao.class));
    }

    @Test
    public void testAccessTokenDao (){
        assertThat(config.accessTokenDao(), instanceOf(IsAccessTokenDao.class));

    }

}