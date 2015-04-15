package tchepannou.mora.rest.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import tchepannou.mora.core.config.CacheConfig;
import tchepannou.mora.core.config.DaoConfig;
import tchepannou.mora.core.config.SecurityServicesConfig;
import tchepannou.mora.swagger.config.SwaggerConfig;

@Configuration
@SpringBootApplication
@Import ({DaoConfig.class, SwaggerConfig.class, SecurityServicesConfig.class, CacheConfig.class})
public class Application {
    //-- Main
    public static void main (String [] args){
        SpringApplication.run(Application.class, args);
    }
}
