package tchepannou.mora.core.config;

import org.junit.Test;
import tchepannou.mora.core.dao.jdbc.JdbcAccessTokenDao;
import tchepannou.mora.core.dao.jdbc.JdbcPasswordDao;
import tchepannou.mora.core.dao.jdbc.JdbcSpaceDao;
import tchepannou.mora.core.dao.jdbc.JdbcSpaceTypeDao;
import tchepannou.mora.core.dao.jdbc.JdbcUserDao;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

public class DaoConfigTest {
    private DaoConfig config = new DaoConfig();

    @Test
    public void testAccessTokenDao() throws Exception {
        assertThat(config.accessTokenDao(), instanceOf(JdbcAccessTokenDao.class));
    }

    @Test
    public void testPasswordDao() throws Exception {
        assertThat(config.passwordDao(), instanceOf(JdbcPasswordDao.class));
    }

    @Test
    public void testUserDao() throws Exception {
        assertThat(config.userDao(), instanceOf(JdbcUserDao.class));
    }

    @Test
    public void testSpaceTypeDao() throws Exception {
        assertThat(config.spaceTypeDao(), instanceOf(JdbcSpaceTypeDao.class));
    }

    @Test
    public void testSpaceDao () throws Exception {
        assertThat(config.spaceDao(), instanceOf(JdbcSpaceDao.class));
    }
}