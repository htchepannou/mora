package tchepannou.mora.core.config;

import org.junit.Test;
import tchepannou.mora.core.dao.jdbc.*;

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

    @Test
    public void testRoleDao (){
        assertThat(config.roleDao(), instanceOf(JdbcRoleDao.class));
    }

    @Test
    public void testMemberDao (){
        assertThat(config.memberDao(), instanceOf(JdbcMemberDao.class));
    }

    @Test
    public void testPostDao (){
        assertThat(config.postDao(), instanceOf(JdbcPostDao.class));
    }

    @Test
    public void testMediaTypeDao (){
        assertThat(config.mediaTypeDao(), instanceOf(JdbcMediaTypeDao.class));
    }

    @Test
    public void testMediaDao (){
        assertThat(config.mediaDao(), instanceOf(JdbcMediaDao.class));
    }

    @Test
    public void testAttachmentTypeDao (){
        assertThat(config.attachmentTypeDao(), instanceOf(JdbcAttachmentTypeDao.class));
    }

}