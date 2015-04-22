package tchepannou.mora.rest.space.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import tchepannou.mora.core.config.CacheConfig;
import tchepannou.mora.core.config.ExtConfig;
import tchepannou.mora.core.config.ServiceConfig;
import tchepannou.mora.insidesoccer.config.IsConfig;

@Configuration
@Import ({ExtConfig.class, IsConfig.class, ServiceConfig.class, CacheConfig.class})
public class Config {   // NOSONAR - Empty class
}
