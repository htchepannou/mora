package tchepannou.mora.insidesoccer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import tchepannou.mora.core.config.ServiceConfig;

@Configuration
@PropertySource ("classpath:config/application.properties")
@Import ({IsDaoConfig.class, ServiceConfig.class})
public class JdbcConfig{
}
