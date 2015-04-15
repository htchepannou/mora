package tchepannou.mora.core.config;

import org.junit.Test;
import org.springframework.cache.support.NoOpCacheManager;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

public class NoOpCacheConfigTest {
    NoOpCacheConfig config = new NoOpCacheConfig();

    @Test
    public void testCacheManager() throws Exception {
        assertThat(config.cacheManager(), instanceOf(NoOpCacheManager.class));
    }
}