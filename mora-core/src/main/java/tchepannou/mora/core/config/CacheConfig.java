package tchepannou.mora.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import ({NoOpCacheConfig.class, EhCacheConfig.class})
public class CacheConfig {
}
