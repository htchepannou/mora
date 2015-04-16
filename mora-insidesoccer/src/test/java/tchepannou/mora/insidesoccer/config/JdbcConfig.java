package tchepannou.mora.insidesoccer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource ("classpath:config/application.properties")
@Import (IsDaoConfig.class)
public class JdbcConfig{
}
