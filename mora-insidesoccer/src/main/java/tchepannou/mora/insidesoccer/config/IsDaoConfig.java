package tchepannou.mora.insidesoccer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tchepannou.mora.core.config.DaoConfig;
import tchepannou.mora.core.dao.AccessTokenDao;
import tchepannou.mora.core.dao.EventDao;
import tchepannou.mora.core.dao.EventTypeDao;
import tchepannou.mora.core.dao.MemberDao;
import tchepannou.mora.core.dao.PostDao;
import tchepannou.mora.core.dao.RoleDao;
import tchepannou.mora.core.dao.SpaceDao;
import tchepannou.mora.core.dao.SpaceTypeDao;
import tchepannou.mora.core.dao.UserDao;
import tchepannou.mora.insidesoccer.dao.NodeAttributeDao;
import tchepannou.mora.insidesoccer.dao.NodeDao;
import tchepannou.mora.insidesoccer.dao.NodePartyRelationshipDao;
import tchepannou.mora.insidesoccer.dao.PartyAttributeDao;
import tchepannou.mora.insidesoccer.dao.PartyDao;
import tchepannou.mora.insidesoccer.dao.PartyRelationshipDao;
import tchepannou.mora.insidesoccer.dao.impl.*;
import tchepannou.mora.insidesoccer.service.TeamResolver;
import tchepannou.mora.insidesoccer.service.impl.TeamResolverImpl;

@Configuration
public class IsDaoConfig extends DaoConfig{
    //-- Public
    @Bean
    public PartyDao partyDao (){
        return new PartyDaoImpl();
    }

    @Bean
    public PartyAttributeDao partyAttributeDao (){
        return new PartyAttributeDaoImpl();
    }

    @Bean
    public NodePartyRelationshipDao nodePartyRelationshipDao(){
        return new NodeRelationshipDaoImpl();
    }

    @Bean
    public NodeDao nodeDao (){
        return new NodeDaoImpl();
    }

    @Bean
    public NodeAttributeDao nodeAttributeDao (){
        return new NodeAttributeDaoImpl();
    }

    @Bean
    public PartyRelationshipDao partyRelationshipDao(){
        return new PartyRelationshipDaoImpl();
    }

    @Bean
    public TeamResolver teamResolver (){
        return new TeamResolverImpl();
    }

    //-- DaoConfig overrides
    @Bean
    @Override
    public PostDao postDao(){
        return new IsPostDao();
    }

    @Bean
    @Override
    public UserDao userDao () {
        return new IsUserDao();
    }

    @Bean
    @Override
    public SpaceDao spaceDao () {
        return new IsSpaceDao();
    }

    @Override
    @Bean
    public RoleDao roleDao() {
        return new IsRoleDao();
    }

    @Override
    @Bean
    public AccessTokenDao accessTokenDao (){
        return new IsAccessTokenDao();
    }

    @Override
    @Bean
    public SpaceTypeDao spaceTypeDao() {
        return new IsSpaceTypeDao();
    }

    @Override
    @Bean
    public MemberDao memberDao() {
        return new IsMemberDao();
    }

    @Override
    @Bean
    public IsMediaTypeDao mediaTypeDao() {
        return new IsMediaTypeDao();
    }

    @Override
    @Bean
    public IsMediaDao mediaDao() {
        return new IsMediaDao();
    }

    @Override
    @Bean
    public IsAttachmentTypeDao attachmentTypeDao() {
        return new IsAttachmentTypeDao();
    }

    @Override
    public EventTypeDao eventTypeDao() {
        return new IsEventTypeDao();
    }

    @Override
    public EventDao eventDao() {
        return new IsEventDao();
    }
}
