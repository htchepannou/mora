package tchepannou.mora.rest.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import tchepannou.mora.core.config.CacheConfig;
import tchepannou.mora.core.config.ExtConfig;
import tchepannou.mora.core.config.ServiceConfig;
import tchepannou.mora.insidesoccer.config.IsConfig;
import tchepannou.mora.swagger.config.SwaggerConfig;

@Configuration
@Import ({ExtConfig.class, IsConfig.class, ServiceConfig.class, CacheConfig.class, SwaggerConfig.class})
public class Config {   // NOSONAR - Empty class
}
