package tchepannou.mora.insidesoccer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tchepannou.mora.core.config.DaoConfig;
import tchepannou.mora.core.dao.AccessTokenDao;
import tchepannou.mora.core.dao.MemberDao;
import tchepannou.mora.core.dao.RoleDao;
import tchepannou.mora.core.dao.SpaceDao;
import tchepannou.mora.core.dao.SpaceTypeDao;
import tchepannou.mora.core.dao.UserDao;
import tchepannou.mora.insidesoccer.dao.NodeDao;
import tchepannou.mora.insidesoccer.dao.PartyAttributeDao;
import tchepannou.mora.insidesoccer.dao.PartyDao;
import tchepannou.mora.insidesoccer.dao.impl.IsAccessTokenDao;
import tchepannou.mora.insidesoccer.dao.impl.IsMemberDao;
import tchepannou.mora.insidesoccer.dao.impl.IsRoleDao;
import tchepannou.mora.insidesoccer.dao.impl.IsSpaceDao;
import tchepannou.mora.insidesoccer.dao.impl.IsSpaceTypeDao;
import tchepannou.mora.insidesoccer.dao.impl.IsUserDao;
import tchepannou.mora.insidesoccer.dao.impl.NodeDaoImpl;
import tchepannou.mora.insidesoccer.dao.impl.PartyAttributeDaoImpl;
import tchepannou.mora.insidesoccer.dao.impl.PartyDaoImpl;

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
    public NodeDao nodeDao(){
        return new NodeDaoImpl();
    }

    //-- DaoConfig overrides
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
}
