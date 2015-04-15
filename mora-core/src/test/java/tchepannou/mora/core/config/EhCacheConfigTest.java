package tchepannou.mora.core.config;

import org.junit.Test;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

public class EhCacheConfigTest {
    EhCacheConfig config = new EhCacheConfig();

    @Test
    public void testCacheManager() throws Exception {
        assertThat(config.cacheManager(), instanceOf(EhCacheCacheManager.class));
    }

    @Test
    public void testEhCacheCacheManager() throws Exception {
        assertThat(config.ehCacheCacheManager(), instanceOf(EhCacheManagerFactoryBean.class));
    }
}