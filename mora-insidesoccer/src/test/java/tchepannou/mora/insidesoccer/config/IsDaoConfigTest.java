package tchepannou.mora.insidesoccer.config;

import org.junit.Test;
import tchepannou.mora.insidesoccer.dao.impl.IsAccessTokenDao;
import tchepannou.mora.insidesoccer.dao.impl.IsRoleDao;
import tchepannou.mora.insidesoccer.dao.impl.IsSpaceTypeDao;
import tchepannou.mora.insidesoccer.dao.impl.PartyDaoImpl;

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

    @Test
    public void testSpaceTypeDao (){
        assertThat(config.spaceTypeDao(), instanceOf(IsSpaceTypeDao.class));
    }

    @Test
    public void testPartyDao (){
        assertThat(config.partyDao(), instanceOf(PartyDaoImpl.class));
    }
}