package tchepannou.mora.insidesoccer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tchepannou.mora.core.config.DaoConfig;
import tchepannou.mora.core.dao.AccessTokenDao;
import tchepannou.mora.core.dao.RoleDao;
import tchepannou.mora.insidesoccer.dao.IsAccessTokenDao;
import tchepannou.mora.insidesoccer.dao.IsRoleDao;

@Configuration
public class IsDaoConfig extends DaoConfig{
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
}
