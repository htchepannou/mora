package tchepannou.mora.insidesoccer.config;

import org.junit.Test;
import tchepannou.mora.insidesoccer.dao.impl.*;
import tchepannou.mora.insidesoccer.service.impl.TeamResolverImpl;

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

    @Test
    public void testPartyAttributeDao (){
        assertThat(config.partyAttributeDao(), instanceOf(PartyAttributeDaoImpl.class));
    }

    @Test
    public void testUserDao (){
        assertThat(config.userDao(), instanceOf(IsUserDao.class));
    }

    @Test
    public void testSpaceDao (){
        assertThat(config.spaceDao(), instanceOf(IsSpaceDao.class));
    }

    @Test
    public void testMemberDao (){
        assertThat(config.memberDao(), instanceOf(IsMemberDao.class));
    }
    
    @Test
    public void testNodePartyRelationshipDao (){
        assertThat(config.nodePartyRelationshipDao(), instanceOf(NodeRelationshipDaoImpl.class));
    }

    @Test
    public void testNodeAttributeDao (){
        assertThat(config.nodeAttributeDao(), instanceOf(NodeAttributeDaoImpl.class));
    }

    @Test
    public void testPostDao (){
        assertThat(config.postDao(), instanceOf(IsPostDao.class));
    }

    @Test
    public void testMediaTypeDao (){
        assertThat(config.mediaTypeDao(), instanceOf(IsMediaTypeDao.class));
    }

    @Test
    public void testMediaDao (){
        assertThat(config.mediaDao(), instanceOf(IsMediaDao.class));
    }


    @Test
    public void testAttachmentTypeDao (){
        assertThat(config.attachmentTypeDao(), instanceOf(IsAttachmentTypeDao.class));
    }

    @Test
    public void testPartyRelationshipDao(){
        assertThat(config.partyRelationshipDao(), instanceOf(PartyRelationshipDaoImpl.class));
    }

    @Test
    public void testTeamResolver(){
        assertThat(config.teamResolver(), instanceOf(TeamResolverImpl.class));
    }
}