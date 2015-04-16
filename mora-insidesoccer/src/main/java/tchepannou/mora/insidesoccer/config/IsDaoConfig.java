package tchepannou.mora.insidesoccer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tchepannou.mora.core.config.DaoConfig;
import tchepannou.mora.core.dao.RoleDao;
import tchepannou.mora.insidesoccer.dao.JdbcIsRoleDao;

@Configuration
public class IsDaoConfig extends DaoConfig{
    @Override
    @Bean
    public RoleDao roleDao() {
        return new JdbcIsRoleDao();
    }
}
