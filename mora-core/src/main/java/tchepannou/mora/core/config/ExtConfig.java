package tchepannou.mora.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

/**
 * Extension point configu
 */
@Configuration
@Import ({AuthConfig.class, DaoConfig.class})
@Profile ("default")
public class ExtConfig {  // NOSONAR - Empty class
}
