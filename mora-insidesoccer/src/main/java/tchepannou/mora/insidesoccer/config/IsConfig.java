package tchepannou.mora.insidesoccer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Configuration
@Import ({IsAuthConfig.class, IsDaoConfig.class})
@Profile ("insidesoccer")
public class IsConfig { // NOSONAR - empty class
}
