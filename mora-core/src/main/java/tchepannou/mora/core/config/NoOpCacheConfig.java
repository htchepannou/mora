package tchepannou.mora.core.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.support.NoOpCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NoOpCacheConfig {
    @Bean
    public CacheManager cacheManager() {
        return new NoOpCacheManager();
    }
}
