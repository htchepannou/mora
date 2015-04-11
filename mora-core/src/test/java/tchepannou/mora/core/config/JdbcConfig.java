package tchepannou.mora.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource ("classpath:config/application.properties")
public class JdbcConfig extends DaoConfig{
}
